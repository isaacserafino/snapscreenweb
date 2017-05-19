/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package samples.gae.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Luke Taylor
 *
 */
@Controller
public class GaeAppController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing() {
		return "landing";
	}

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String home() {
		return "guestbook";
	}

	@RequestMapping(value = "/disabled.htm", method = RequestMethod.GET)
	public String disabled() {
		return "disabled";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();

		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL(
				"/loggedout.htm");

		response.sendRedirect(logoutUrl);
	}

	@RequestMapping(value = "/loggedout.htm", method = RequestMethod.GET)
	public String loggedOut() {
		return "loggedout";
	}

	@RequestMapping("/loggedIn")
	public ModelAndView listGuestbook() {
		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();

		if (currentUser == null) {
			return new ModelAndView("redirect:"
					+ userService.createLoginURL("/home.htm"));
		} else {
			return new ModelAndView("guestbook", "welcomeMsg", "You are authenticated, "
					+ currentUser.getNickname());
		}
	}

	@RequestMapping("/sign")
	public String signGuestbook(
			@RequestParam(required = true, value = "guestbookName") String guestbookName,
			@RequestParam(required = true, value = "content") String content,
			Model model) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
		Date date = new Date();
		Entity greeting = new Entity("Greeting", guestbookKey);
		greeting.setProperty("user", user);
		greeting.setProperty("date", date);
		greeting.setProperty("content", content);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(greeting);

		model.addAttribute("guestbookName", guestbookName);
		return "guestbook";
	}

}
