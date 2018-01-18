package com.stackroute.activitystream.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.model.UserCircle;
import com.stackroute.activitystream.repository.CircleRepository;
import com.stackroute.activitystream.repository.UserCircleRepository;
import com.stackroute.activitystream.repository.UserRepository;
import com.stackroute.activitystream.service.UserCircleService;

/*
* Service classes are used here to implement additional business logic/validation.
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn’t currently 
* provide any additional behavior over the @Component annotation, but it’s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service

public class UserCircleServiceImpl implements UserCircleService {

	/*
	 * Autowiring should be implemented for the UserRepository,
	 * CircleRepository, UserCircleRepository. Please note that we should not
	 * create any object using the new keyword
	 
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CircleRepository circleRepo;

	@Autowired
	private UserCircleRepository userCircleRepo;

	
	 * This method should be used to add a user to a specific circle. You need
	 * to validate whether the user and also the circle exist. Also, please
	 * check if the user is already added to the circle. Call the corresponding
	 * method of Respository interface.
	 * 
	 
	public boolean addUser(String username, String circleName) {
		try {
			UserCircle userCircle = new UserCircle(username, circleName);
			if (userRepo.findOne(username) != null && circleRepo.findOne(circleName) != null) {
				if (get(username, circleName) == null) {
					userCircleRepo.save(userCircle);
				}
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	 * This method should be used to remove a user from a specific circle.
	 * Please check if the user is added to the circle before trying to remove.
	 * Call the corresponding method of Respository interface.
	 * 
	 
	public boolean removeUser(String username, String circleName) {
		try {
			UserCircle userCircle = new UserCircle(username, circleName);
			userCircleRepo.delete(userCircle);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	 * This method should be used to show circles subscribed by a specific user.
	 * Call the corresponding method of Respository interface.
	 * 
	 
	public List<String> getMyCircles(String username) {
		return userCircleRepo.findCircleNameByUserName(username);
	}

	
	 * This method should be used to check whether a specific user has
	 * subscribed to a specific circle. Call the corresponding method of
	 * Respository interface.
	 * 
	 
	public UserCircle get(String username, String circleName) {
		return userCircleRepo.getUsernameAndCircleName(username, circleName);
	}
*/
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CircleRepository circleRepository;
	@Autowired
	private UserCircleRepository userCircleRepository;
	public boolean addUser(String username, String circleName) {
		UserCircle userCircle = new UserCircle();
		userCircle.setCircleName(circleName);
		userCircle.setUsername(username);
		try {
			if (userRepository.findOne(username) != null && circleRepository.findOne(circleName) != null) {
				if (userCircleRepository.getUsernameAndCircleName(username, circleName) == null) {
					userCircleRepository.save(userCircle);
				}
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean removeUser(String username, String circleName) {
		UserCircle userCircle = userCircleRepository.getUsernameAndCircleName(username, circleName);
		try {
			userCircleRepository.delete(userCircle);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<String> getMyCircles(String username) {
		return userCircleRepository.findCircleNameByUserName(username);
	}
	public UserCircle get(String username, String circleName) {
		return userCircleRepository.getUsernameAndCircleName(username, circleName);
	}
}
