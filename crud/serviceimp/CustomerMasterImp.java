package com.crud.serviceimp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.springframework.stereotype.Service;

import com.crud.config.JWTtokenUtil;
import com.crud.keycloak.KeyCloakConfig;
import com.crud.loginDto.LoginDto;
import com.crud.model.CustomerMaster;
import com.crud.repository.CustomerMasterRepo;
import com.crud.service.CustomerMasterService;
import com.crud.service.JwtUserDetailsService;

@Service
public class CustomerMasterImp implements CustomerMasterService {

	@Autowired
	private CustomerMasterRepo customerrepo;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	JWTtokenUtil jwTtokenUtil;
	
//	@Autowired
//	private KeyCloakConfig keycloakConfig;

	@Value("${keycloak.resource}")
	private String clientId;

	@Value("${keycloak.token}")
	private String keyCloakToken;

	@Value("${keycloak.auth-server-url}")
	private String authServerUrl;

	@Value("${keycloak.realm}")
	private String realmName;
	// jwt token generation method
	
	

	
	@Override
	public Object validateDto(LoginDto logindto) throws IOException {
		Map<String,Object>map=new HashMap<>();
		String userName=logindto.getUserName();
		String password=logindto.getPassword();
		CustomerMaster customer = customerrepo.findUserName(userName);
		
		UserDetails userDetails = new User(customer.getUserName(), customer.getPassword(),  new ArrayList<>());
		
		if(customer !=null)
		{
			StringBuilder param = new StringBuilder();
			param.append("username=");
			param.append(userName);
			param.append("&password=");
			param.append(password);
			param.append("&client_id=");
			param.append(clientId);
			param.append("&grant_type=password");

			byte[] postData = param.toString().getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;

			String request = keyCloakToken;
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			conn.setUseCaches(false);
			String responseData = "";
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
				wr.close();

				int responseCode = conn.getResponseCode();
				if (responseCode == 200) {

					InputStream is = conn.getInputStream();
					BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
					if ((responseData = responseReader.readLine()) != null) {
						System.out.append("Response: " + responseData);
						JSONObject response = new JSONObject(responseData);
						map.put("access_token", response.get("access_token"));
						return map;
					}
					responseReader.close();

			if(customer.getPassword().equals(password))
			{
				final String token = jwTtokenUtil.generateToken(userDetails);
				map.put("messgae","Login Successfull");
				map.put("customerId", customer.getCustomerId());
				map.put("name",customer.getName());
				map.put("token", token);
				return map;
			}
			else
			{
				map.put("messgae","Password is invalid");
				return map;
			}
		}
		else
		{
			map.put("messgae","UserName is Invalid");
			return map;
		}	
			
			}
		}
		return map;
	} 
		
	
	@Override
	public Object getCustomer(int customerId) {
		Map<String, Object> map = new HashMap<>();
		CustomerMaster customerMaster = customerrepo.findById(customerId).orElse(null);
		if (customerMaster != null)

		{
			map.put("Name", customerMaster.getName());
			map.put("username", customerMaster.getUserName());
			map.put("dob", customerMaster.getDob());
			map.put("Email", customerMaster.getEmail());
			map.put("phoneNo", customerMaster.getPhoneNo());
			map.put("door_street", customerMaster.getDoorStreet());
			map.put("panNo", customerMaster.getPanNo());
			map.put("city", customerMaster.getCity());
			map.put("state", customerMaster.getState());
			map.put("pincode", customerMaster.getPincode());
			map.put("password", customerMaster.getPassword());
	

		} else {
			map.put("Status", "Error");
			map.put("Msg", "Invalid customerId");
		}
		return map;

	}

	@Override
	public Object updatecustomer(CustomerMaster customerMaster) {
		Map<String, Object> map = new HashMap<>();
		CustomerMaster customerMaster1 = customerrepo.findById(customerMaster.getCustomerId()).orElse(null);

		if (customerMaster1 != null) {

			if (customerMaster.getName().isEmpty() || customerMaster.getName() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid name");
			}
			else if (customerMaster.getUserName().isEmpty() || customerMaster.getUserName() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid username");
			} 
			else if (customerMaster.getPassword().isEmpty()|| customerMaster.getPassword() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid password");
			} 
		 else if (customerMaster.getDob() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the dob");
			} else if (customerMaster.getDoorStreet().isEmpty() || customerMaster.getDoorStreet() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid door_street");
			} else if (customerMaster.getCity().isEmpty()) {
				map.put("status", "error");
				map.put("msg", "please enter the valid city");
			} else if (customerMaster.getState().isEmpty() || customerMaster.getState() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid state");
			} else if (customerMaster.getPanNo() == null || customerMaster.getPanNo().isEmpty()) {
				map.put("status", "error");
				map.put("msg", "please enter the valid pan_no");
			} else if (customerMaster.getPhoneNo().isEmpty() || customerMaster.getPhoneNo() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid phone_num");
			} else if (customerMaster.getEmail().isEmpty() || customerMaster.getEmail() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid email");
			} else if (customerMaster.getPincode().isEmpty() || customerMaster.getPincode() == null) {
				map.put("status", "error");
				map.put("msg", "please enter the valid pincode");
			} 
			else {
	
				customerrepo.saveAndFlush(customerMaster);
				map.put("status", "success");
				map.put("msg", "Data updated successfully");
			}
		} else {
			map.put("status", "error");
			map.put("msg", "invalid customer");
		}
		return map;

	}

	

	

}
