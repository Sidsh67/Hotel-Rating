package com.project.userservice.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.project.userservice.entity.Hotel;
import com.project.userservice.entity.Rating;
import com.project.userservice.entity.User;
import com.project.userservice.exception.ResourceNotFoundException;
import com.project.userservice.external.services.HoterService;
import com.project.userservice.external.services.RatingService;
import com.project.userservice.repository.UserRepository;
import com.project.userservice.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepositoy;
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private HoterService hotelServices;
	
	@Autowired
	private RatingService ratingService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
// Save User using Rest-Template
/*	public User saveUser(User user) {
		String randomId=UUID.randomUUID().toString();
		user.setUser_id(randomId);
System.out.println("user============="+user.toString());
//GET RATING BY REST TEMPLATE
		List<Rating> userRatings = user.getRatings();
		System.out.println("userRating============="+userRatings);
		for (Rating rating : userRatings) {
			
			//GET HOTEL BY REST TEMPLATE
			Hotel hotel=rating.getHotel();
			ResponseEntity<Hotel> forEntity = template.postForEntity("http://HOTELSERVICE/hotel",hotel ,Hotel.class);
			Hotel hotelResponse= forEntity.getBody();
			rating.setUserId(randomId);
			rating.setHotelId(hotelResponse.getId());
			ResponseEntity<Rating> ratingResponse= template.postForEntity("http://RATINGSERVICE/rating",rating, Rating.class);
			Rating response= ratingResponse.getBody();
			hotel.setId(hotelResponse.getId());
			rating.setRatingId(response.getRatingId());
			System.out.println("***********************"+rating);
		}
		User resp=userRepositoy.save(user);
		resp.setRatings(userRatings);
		return  resp;
	}*/
	
	
// Save User using Feign Client
		public User saveUser(User user) {
			String randomId=UUID.randomUUID().toString();
			user.setUser_id(randomId);
	System.out.println("user============="+user.toString());
	//GET RATING BY REST TEMPLATE
			List<Rating> userRatings = user.getRatings();
			System.out.println("userRating============="+userRatings);
			for (Rating rating : userRatings) {
				
				//GET HOTEL BY REST TEMPLATE
				Hotel hotel=rating.getHotel();
				//ResponseEntity<Hotel> forEntity = template.postForEntity("http://HOTELSERVICE/hotel",hotel ,Hotel.class);
				Hotel hotelResponse= hotelServices.saveHotel(hotel);
				
				rating.setUserId(randomId);
				rating.setHotelId(hotelResponse.getId());
				
//				ResponseEntity<Rating> ratingResponse= template.postForEntity("http://RATINGSERVICE/rating",rating, Rating.class);
				Rating response= ratingService.create(rating);
				hotel.setId(hotelResponse.getId());
				rating.setRatingId(response.getRatingId());
				System.out.println("***********************"+rating);
			}
			User resp=userRepositoy.save(user);
			resp.setRatings(userRatings);
			return  resp;
		}
	
	
//Get Single User by Id using Rest-Template
	/*public User getUsersDetail(String userId) {
		User user=userRepositoy.findById(userId).
		orElseThrow(()
			->new ResourceNotFoundException(
				"user with given id not found on exception"));
		System.out.println(user);
		
		Rating[] forObject= template.getForObject("http://RATINGSERVICE/rating/user/"+user.getUser_id(), Rating[].class);
		List<Rating> ratings=Arrays.asList(forObject);
		
		user.setRatings(ratings);
		
		for (Rating r : ratings) {
			
			
			String strid=r.getHotelId();
			Hotel hotel = template.getForObject("http://HOTELSERVICE/hotel/"+strid, Hotel.class);
			r.setHotel(hotel);
		}
//		List<Rating> ratings=Arrays.stream(forObject).toList();
//		System.out.println(forObject+" {} ");
//		List<Rating> ratingList=new ArrayList<>();
//		List<HotelAndUserAndRating> response =new ArrayList<>();
//		List<Hotel> hotelList=new ArrayList<>();
//		HotelAndUserAndRating obj=new HotelAndUserAndRating();
//		
//		obj.setUser(user);
//		
//		for(Rating rating:ratings) {
//			
//			ResponseEntity<Hotel> forEntity = template.getForEntity("http://localhost:8070/hotel/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel=forEntity.getBody();
//			hotelList.add(hotel);
//			ratingList.add(rating);
//			
//		}
//		obj.setHotel(hotelList);
//		obj.setRating(ratingList);
//		System.out.println(obj+"   OBJ");
//		System.out.println(response+"   response");
//		return obj;

		return user;
		
	}*/
	
//Get Single User by Id using Feign-Client
	public User getUsersDetail(String userId) {
		User user=userRepositoy.findById(userId).
		orElseThrow(()
			->new ResourceNotFoundException(
				"user with given id not found on exception"));
		System.out.println(user);
		
//		Rating[] forObject= template.getForObject("http://RATINGSERVICE/rating/user/"+user.getUser_id(), Rating[].class);
//		List<Rating> ratings=Arrays.asList(forObject);
		List<Rating> ratings=  ratingService.getRatingById(userId);
		user.setRatings(ratings);
		
		for (Rating r : ratings) {
			
			System.out.println("R "+r);
			String strid=r.getHotelId();
			
			Hotel hotel= hotelServices.getHotel(strid);
			r.setHotel(hotel);
		}
		return user;
		
	}
	
		
		
	
//Get AllUser
/*	public List<User> getAllUser(){
		
	List<User> users=userRepositoy.findAll();
	
	for (User user : users) {
		
		Rating[] rating = template.getForObject("http://RATINGSERVICE/rating/user/"+user.getUser_id(), Rating[].class);
		List<Rating> ratings=Arrays.asList(rating);
		
		user.setRatings(ratings);
		
		for (Rating r : ratings) {
			
			
			String strid=r.getHotelId();
			Hotel hotel =   template.getForObject("http://HOTELSERVICE/hotel/"+strid, Hotel.class);
			r.setHotel(hotel);
		}

	}
	////	System.out.println(users+"/////////////////");
//	
//	for(User user1:users) {
////		ResponseEntity<List<Rating>> ratings=template.exchange("http://localhost:8060/rating/user"+user1.getUser_id(),HttpMethod.GET,null,new ParameterizedTypeReference<List<Rating>>() {});
////		ResponseEntity<Rating[]> rating=template.getForEntity("http://localhost:8060/rating/user"+user1.getUser_id(), Rating[].class);
//		
//		
//	
//		Rating[] rating = template.getForObject("http://localhost:8060/rating/user/"+user1.getUser_id(), Rating[].class);
//		List<Rating> ratings=Arrays.asList(rating);
//		System.out.println(ratings);
//		
//		user1.setRatings(ratings);
//		
//		for(Rating r: ratings) {
//			
//			
// 
//		ResponseEntity<Hotel> hotel=template.getForEntity("http://localhost:8070/hotel/"+r.getHotelId(), Hotel.class);
//		Hotel h= hotel.getBody();
//		r.setHotel(h);
//		}
//		
//		System.out.println("========================= "+user1.getUser_id()+" "+user1.getEmail()+" "+user1.getRatings()+" "+user1.getName());
//	}

	return users;
	}*/
	
	//Get AllUser
		public List<User> getAllUser(){
			
		List<User> users=userRepositoy.findAll();
		
		for (User user : users) {
			
//			Rating[] rating = template.getForObject("http://RATINGSERVICE/rating/user/"+user.getUser_id(), Rating[].class);
//			List<Rating> ratings=Arrays.asList(rating);
			List<Rating> ratings= ratingService.getRatingById(user.getUser_id());
			
			user.setRatings(ratings);
			
			for (Rating r : ratings) {
				
				
				String strid=r.getHotelId();
				Hotel hotel =   template.getForObject("http://HOTELSERVICE/hotel/"+strid, Hotel.class);
				r.setHotel(hotel);
			}

		}
		return users;
		}
	



//Update User by Rest Template
/*	public User updateUser(String userId, User user2) {
		System.out.println(user2);
		List<Rating> ratingsList=user2.getRatings();
		System.out.println(ratingsList);
		
		for (Rating rating : ratingsList) {
			rating.setUserId(user2.getUser_id());
			if(rating.getRatingId() != null) {
			template.put("http://RATINGSERVICE/rating/"+rating.getRatingId(), rating);
		}
			
			System.out.println(rating.getHotel());
//			Hotel hotel=rating.getHotel();
//			
//			System.out.println("Hotel id  "+hotel.getId());
		}
		return user2;
	}*/
	
//Update User using Feign-Client
		public User updateUser(String userId, User user2) {
			
//			System.out.println(user2.getRatings());
			List<Rating> ratingsList=user2.getRatings();
//			System.out.println("++++++++  "+ratingsList);
			
			for (Rating rating : ratingsList) {
				rating.setUserId(user2.getUser_id());
				
				if(rating.getRatingId() != null) {
					Rating response= ratingService.update(rating.getRatingId(), rating);
//					rating.setHotelId(response.getRatingId());
					rating.setHotelId(response.getHotelId());
					
//					System.out.println("Rating Id  is  "+response.getRatingId());
//					System.out.println("     ");
			}
				
				Hotel hotel=rating.getHotel();
				
				System.out.println("Hotel id  "+hotel.getId());
			}
			return user2;
		}
	
	
	
	
	
//	public User updateUser1(String userId, User user2) {
//		
//		
////		System.out.println(userId+ " "+ user2.getRatings());
//	
//		List<Rating> ratings = user2.getRatings();
//		
//		
//		for (Rating rating2 : ratings) {
//			
////			System.out.println("rating id "+rating2.getRatingId());
//			
//			if(rating2.getRatingId()!=null) {
//				
//				System.out.println(rating2);
//				
//				template.put("http://localhost:8060/rating/"+rating2.getRatingId(), rating2);
//			}
//			
//		}
//		return user2;
//}
	
	//Update User via patch using Rest Template
	/*	public User updateUserViaPatch(String userId, User user2) {
			System.out.println(user2);
			
			List<Rating> ratingsList=user2.getRatings();
			System.out.println(ratingsList);
			
			for (Rating rating : ratingsList) {
				rating.setUserId(user2.getUser_id());
				if(rating.getRatingId() != null) {
				template.patchForObject("http://RATINGSERVICE/rating/"+rating.getRatingId(), rating,  Rating.class);
			}
				
				System.out.println(rating.getHotel());
//				Hotel hotel=rating.getHotel();
//				
//				System.out.println("Hotel id  "+hotel.getId());
			}
			return user2;
			
		
		}*/
		
		
		//Update User via patch using Feign Client
				public User updateUserViaPatch(String userId, User user2) {
					System.out.println( "users ====>"+user2 );
					
					List<Rating> ratingsList=user2.getRatings();
					
					System.out.println("your rating list "+ratingsList);
					
					for (Rating rating : ratingsList) {
						
						rating.setUserId(userId);
						
						if(rating.getRatingId() != null) {
							System.out.println("Inside rating ");
							
							try {
								System.out.println(rating.toString()+"===================================");
								Rating updateViaPatch = ratingService.updateViaPatch(rating, rating.getRatingId());
								System.out.println("updated "+updateViaPatch);
							} catch (Exception e) {
								System.out.println(e );
							}
					}
						
					
//						Hotel hotel=rating.getHotel();
//						
//						System.out.println("Hotel id  "+hotel.getId());
					}
					return user2;
					
				
				}
		
		
//Remove by Rest template
/*	@Override
	public boolean removebyId(String id) {
		// TODO Auto-generated method stub
 		User user = userRepositoy.findById(id).get();
		Rating[] rating = template.getForObject("http://RATINGSERVICE/rating/user/"+user.getUser_id(), Rating[].class);
		List<Rating> ratings=Arrays.asList(rating);
		
		boolean f=false;
		
		for (Rating rating2 : ratings) {
			
			if((rating2.getRatingId())!=null) {
				
				template.delete("http://RATINGSERVICE/rating/"+rating2.getRatingId());
				f= true;
			}
		}
		
		
		return f;
	}*/
//Delete by Feign Client		
		public boolean removebyId(String id) {
			// TODO Auto-generated method stub
			
	 		User user = userRepositoy.findById(id).get();
//			Rating[] rating = template.getForObject("http://RATINGSERVICE/rating/user/"+user.getUser_id(), Rating[].class);
//			List<Rating> ratings=Arrays.asList(rating);
	 		List<Rating> ratings=  ratingService.getRatingById(id);
		
			boolean f=false;
			
			for (Rating rating2 : ratings) {
			
				if((rating2.getRatingId())!=null) {
					System.out.println("++++++++++++++++++++"+rating2.getRatingId() );
//					template.delete("http://RATINGSERVICE/rating/"+rating2.getRatingId());
					
					ratingService.deleteBy(rating2.getRatingId());
					
					f= true;
					System.out.println("++++++++++++++++++++");
				}
				System.out.println("++++++++++++++++++++"+" End ");
			}
			
			
			return f;
		}
	
	
	
	
}
