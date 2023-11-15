package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.WishListDto;
import com.spotify.wishlistservice.entity.WishList;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.repository.WishListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class WishListServiceImpl implements WishListService{

    private final WishListRepository wishListRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository, ModelMapper modelMapper) {
        this.wishListRepository = wishListRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WishListDto> getAllUsers() {
        return Stream.of(wishListRepository.findAll())
                .flatMap(entityList -> entityList.stream()
                        .map(entity -> modelMapper.map(entity, WishListDto.class))).toList();
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
}
