package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishListDto;
import com.spotify.wishlistservice.fiegnClient.MusicFeignClient;
import com.spotify.wishlistservice.model.WishList;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.repository.WishListRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class WishListServiceImpl implements WishListService{

    private final WishListRepository wishListRepository;

    private final ModelMapper modelMapper;

    private final MusicFeignClient musicFeignClient;

    private final RestTemplate restTemplate;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository, ModelMapper modelMapper, MusicFeignClient musicFeignClient, RestTemplate restTemplate) {
        this.wishListRepository = wishListRepository;
        this.modelMapper = modelMapper;
        this.musicFeignClient = musicFeignClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<WishListDto> getAllByUsername(String username) {
        return Stream.of(wishListRepository.findAllByUsername(username))
                .flatMap(entityList -> entityList.stream()
                        .map(entity -> modelMapper.map(entity, WishListDto.class))).toList();
    }

    @Override
    public WishListDto saveWishList(WishList wishList) {

        return modelMapper.map(wishListRepository.save(wishList),WishListDto.class);
    }

    @Override
    public WishListDto removeWishList(long id) {

        WishList wishList = wishListRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("YourEntity not found with id: "+id));

        wishListRepository.deleteById(id);
        return modelMapper.map(wishListRepository.save(wishList),WishListDto.class);
    }

    @Override
    public TrackDto getTrack(String trackId) {
        return modelMapper.map(musicFeignClient.getTrack(trackId),TrackDto.class);
    }


}
