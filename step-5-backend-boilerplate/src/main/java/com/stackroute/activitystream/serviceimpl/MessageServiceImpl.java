package com.stackroute.activitystream.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.UserTag;
import com.stackroute.activitystream.repository.CircleRepository;
import com.stackroute.activitystream.repository.MessageRepository;
import com.stackroute.activitystream.repository.UserCircleRepository;
import com.stackroute.activitystream.repository.UserRepository;
import com.stackroute.activitystream.repository.UserTagRepository;
import com.stackroute.activitystream.service.MessageService;

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
public class MessageServiceImpl implements MessageService{
	
	/*private static int pageSize = 8;
	
	 * Autowiring should be implemented for the CircleRepository, UserRepository,
	 * UserTagRepository, MessageRepository, UserCircleRepository.
	 *  Please note that we should not create any object using the new keyword
	 * 
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserCircleRepository userCircleRepo;
	
	@Autowired
	private CircleRepository circleRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private UserTagRepository userTagRepo;
	
	 * This method should be used to get all messages from a specific circle. Call the corresponding method of Respository interface.
	 * 
	public List<Message> getMessagesFromCircle(String circleName,int pageNumber) {
		//return messageRepo.getMessagesFromCircle(circleName).subList(pageNumber, pageSize);
		return messageRepo.getMessagesFromCircle(circleName); 
	}
	
	
	 * This method should be used to get all messages from a specific user to another
	 * specific user. Call the corresponding method of Respository interface.
	 
	public List<Message> getMessagesFromUser(String username,String otherUsername,int pageNumber) {
		
		//return messageRepo.getMessagesFromUser(username, otherUsername).subList(pageNumber, pageSize);
		return messageRepo.getMessagesFromUser(username, otherUsername);
	}
	
	
	 * This method should be used to send messages to a specific circle. Please validate
	 * whether the circle exists and whether the sender is subscribed to the circle. Call the corresponding method of Respository interface.
	 
	public boolean sendMessageToCircle(String circleName,Message message) {
		try{
			message.setPostedDate();
			message.setCircleName(circleName);
			
			if(circleRepo.findOne(circleName)!=null || !(userCircleRepo.findCircleNameByUserName(message.getSenderName()).contains(circleName))) {
				return false;
			}
			messageRepo.save(message);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	
	}
	
	
	
	 * This method should be used to send messages to a specific user. Please validate
	 * whether the sender and receiver are valid users. Call the corresponding method of Respository interface.
	 
	public boolean sendMessageToUser(String username,Message message) {
		try{
			message.setPostedDate();
			message.setReceiverId(username);
			if(userRepo.findOne(message.getSenderName())!=null && userRepo.findOne(message.getReceiverId())!=null){
				return true;
			}else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;	
		}
		
		
	} 	
	
	
	 * This method should be used to list out all tags from all existing messages. Call the corresponding method of Respository interface.
	 
	public List<String> listTags() {
		
		return messageRepo.listAllTags();
		
	}
	
	
	 * This method should be used to list out all subscribed tags by a specific
	 * user. Call the corresponding method of Respository interface.
	 
	@SuppressWarnings("unchecked")
	public List<String> listMyTags(String username) {
		//return (List<String>) userTagRepo.findOne(username);
		return messageRepo.listMyTags(username);
	}
	
	
	 * This method should be used to show all public messages(messages sent to circles)
	 * containing a specific tag. Call the corresponding method of Respository interface. 
	 
	public List<Message> showMessagesWithTag(String tag,int pageNumber) {
		
		//return messageRepo.showMessagesWithTag(tag).subList(pageNumber, pageSize);
		return messageRepo.showMessagesWithTag(tag);
	}
	
	
	 * This method should be used to subscribe a user to a specific tag. Call the corresponding method of Respository interface.
	 
	public boolean subscribeUserToTag(String username, String tag) {
		try{
			UserTag userTag = new UserTag();
			userTag.setUsername(username);
			userTag.setTag(tag);
			if(!(listMyTags(username).contains(tag))){
				userTagRepo.save(userTag);
				return true;
			}else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	 * This method should be used to unsubscribe a user from a specific tag. Call the corresponding method of Respository interface.
	 
	public boolean unsubscribeUserToTag(String username, String tag) {
	
		try{
			UserTag userTag = new UserTag();
			userTag.setUsername(username);
			userTag.setTag(tag);
			if(!(listMyTags(username).contains(tag))){
				userTagRepo.delete(userTag);
				return true;
			}else
				return false;
			userTag = userTagRepo.getUserTag(username, tag);
			if (listMyTags(username).contains(tag)) {
				userTagRepo.delete(userTag);
				return true;
			} else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}*/
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserTagRepository userTagRepository;
	@Autowired
	private CircleRepository circleRepository;
	@Autowired
	private UserCircleRepository userCircleRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserTag userTag;
	public List<Message> getMessagesFromCircle(String circleName, int pageNumber) {
		return messageRepository.getMessagesFromCircle(circleName);
	}
	public List<Message> getMessagesFromUser(String username, String otherUsername, int pageNumber) {
		return messageRepository.getMessagesFromUser(username, otherUsername);
	}
	public boolean sendMessageToCircle(String circleName, Message message) {
		message.setPostedDate();
		message.setCircleName(circleName);
		if (circleRepository.findOne(circleName) == null
				|| !(userCircleRepository.findCircleNameByUserName(message.getSenderName()).contains(circleName))) {
			return false;
		} else {
			messageRepository.save(message);
			return true;
		}
	}
	public boolean sendMessageToUser(String username, Message message) {
		message.setPostedDate();
		message.setReceiverId(username);
		if ((userRepository.findOne(message.getSenderName()) != null)
				&& (userRepository.findOne(message.getReceiverId()) != null)) {
			messageRepository.save(message);
			return true;
		} else
			return false;
	}
	public List<String> listTags() {
		return messageRepository.listAllTags();
	}
	public List<String> listMyTags(String username) {
		return messageRepository.listMyTags(username);
	}
	public List<Message> showMessagesWithTag(String tag, int pageNumber) {
		return messageRepository.showMessagesWithTag(tag);
	}
	public boolean subscribeUserToTag(String username, String tag) {
		userTag.setTag(tag);
		userTag.setUsername(username);
		if (!(listMyTags(username).contains(tag))) {
			userTagRepository.save(userTag);
			return true;
		} else
			return false;
	}
	public boolean unsubscribeUserToTag(String username, String tag) {
		userTag = userTagRepository.getUserTag(username, tag);
		if (listMyTags(username).contains(tag)) {
			userTagRepository.delete(userTag);
			return true;
		} else
			return false;
	}


}
