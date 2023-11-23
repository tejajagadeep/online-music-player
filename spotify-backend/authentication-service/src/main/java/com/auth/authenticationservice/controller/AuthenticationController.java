  package com.auth.authenticationservice.controller;


  import com.auth.authenticationservice.filter.JwtUtils;
  import com.auth.authenticationservice.model.UserDetails;
  import com.auth.authenticationservice.service.UserService;
  import io.jsonwebtoken.Jwts;
  import io.jsonwebtoken.SignatureAlgorithm;
  import jakarta.servlet.ServletException;
  import lombok.extern.slf4j.Slf4j;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  import java.util.Date;
  import java.util.HashMap;
  import java.util.Map;

  @RequestMapping("/api/v1.0/auth")
  @RestController
  @CrossOrigin(origins = "*")
  @Slf4j
  public class AuthenticationController
  {
      private Map<String,String> mapObj = new HashMap<>();

      @Value("${secret.key}")
      String secret;


      private final UserService userService;

      private final JwtUtils jwtUtils;

      @Autowired
      public AuthenticationController(UserService userService, JwtUtils jwtUtils) {
          this.userService = userService;
          this.jwtUtils = jwtUtils;
      }

      public String generateToken(String username, String password) throws ServletException
      {
          String jwtToken;

          if(username==null || password == null)
          {
              throw new ServletException("Please enter valid username and password");
          }

          boolean flag= userService.loginUser(username, password);
          String role=userService.getRoleByUserAndPass(username,password);
          log.info(role+"--from dbb-- inside token"+username);

          if(!flag)
          {
              throw new ServletException("Invalid credentials");

          }
          else
          {
              log.info(role+"--last---");

              jwtToken=Jwts.builder().setSubject(username).claim("role",role)
                      .setIssuedAt(new Date(System.currentTimeMillis())).
                      setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                      .signWith(SignatureAlgorithm.HS256,secret).compact();

          }

          log.info(role+"--last---");
          log.info(jwtToken+"------token");
          return jwtToken;
      }

      @PostMapping("/login")
      public ResponseEntity<Object> performLogin(@RequestBody UserDetails user)
      {
          log.info(user.getUsername()+"----");
          boolean check=userService.loginUser(user.getUsername(),user.getPassword());
          log.info(check+"");
          if(check){
              String role=userService.getRoleByUserAndPass(user.getUsername(), user.getPassword());
              log.info(role);
              try
              {
                  log.info(generateToken(user.getUsername(), user.getPassword()));
                  String jwtToken = generateToken(user.getUsername(), user.getPassword());
                  log.info(user.getPassword());
                  log.info(jwtToken+"inside login");
                  if(role.equalsIgnoreCase("admin"))
                  {
                      mapObj.put("message", "Admin successfully logged in");
                      mapObj.put("jwtToken",jwtToken);
                      mapObj.put("role",role);
                      return new ResponseEntity<>(mapObj, HttpStatus.CREATED);

                  }
                  else if(role.equalsIgnoreCase("User"))
                  {

                      mapObj.put("message", "User successfully logged in");
                      mapObj.put("jwtToken", jwtToken);
                      mapObj.put("role",role);
                      return new ResponseEntity<>(mapObj, HttpStatus.CREATED);
                  }

              }

              catch( ServletException e)
              {
                  log.info(e+"exception");
                  mapObj.put("message", "User not logged in!");
                  mapObj.put("jwtToken", null);
                  mapObj.put("user not found",null);
              }

          }
          return new ResponseEntity<>(mapObj, HttpStatus.OK);

      }

      @PostMapping("/validate")
      public ResponseEntity<Object> validateToken(@RequestHeader("Authorization") String token) {
          log.info(token+"---------");
          if (jwtUtils.validateJwtToken(token)) {
              log.info("-------validate-------");
              Map<String, String> userInfo = new HashMap<>();
              String authToken = token.substring(7);
              String username = jwtUtils.getUserNameFromJwtToken(authToken);
              String role = jwtUtils.getRoleFromToken(authToken);
              userInfo.put(username, role);
              log.info(userInfo.toString());
              return ResponseEntity.status(HttpStatus.OK).body(userInfo);
          } else {
              log.info("-------***********-------");
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
          }
      }
  }















