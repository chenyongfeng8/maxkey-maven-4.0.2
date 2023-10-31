/*
 * Copyright [2020] [MaxKey of copyright http://www.maxkey.top]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.dromara.maxkey.web.apps.contorller;

import org.dromara.maxkey.authn.annotation.CurrentUser;
import org.dromara.maxkey.constants.ConstsProtocols;
import org.dromara.maxkey.crypto.ReciprocalUtils;
import org.dromara.maxkey.entity.Message;
import org.dromara.maxkey.entity.UserInfo;
import org.dromara.maxkey.entity.apps.AppsFormBasedDetails;
import org.dromara.maxkey.persistence.service.AppsFormBasedDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value={"/apps/formbased"})
public class FormBasedDetailsController  extends BaseAppContorller {
	static final  Logger logger = LoggerFactory.getLogger(FormBasedDetailsController.class);
	
	@Autowired
	AppsFormBasedDetailsService formBasedDetailsService;
	
	@RequestMapping(value = { "/init" }, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> init() {
		AppsFormBasedDetails formBasedDetails=new AppsFormBasedDetails();
		formBasedDetails.setId(formBasedDetails.generateId());
		formBasedDetails.setProtocol(ConstsProtocols.FORMBASED);
		formBasedDetails.setSecret(ReciprocalUtils.generateKey(""));
		return new Message<AppsFormBasedDetails>(formBasedDetails).buildResponse();
	}
	
	@RequestMapping(value = { "/get/{id}" }, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		AppsFormBasedDetails formBasedDetails=formBasedDetailsService.getAppDetails(id , false);
		decoderSecret(formBasedDetails);
		decoderSharedPassword(formBasedDetails);
		formBasedDetails.transIconBase64();
		return new Message<AppsFormBasedDetails>(formBasedDetails).buildResponse();
	}
	
	@ResponseBody
	@RequestMapping(value={"/add"}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> add(
			@RequestBody AppsFormBasedDetails formBasedDetails,
			@CurrentUser UserInfo currentUser) {
		logger.debug("-Add  : {}" , formBasedDetails);
		
		transform(formBasedDetails);
		formBasedDetails.setInstId(currentUser.getInstId());
		if (formBasedDetailsService.insert(formBasedDetails)
				&&appsService.insertApp(formBasedDetails)) {
			return new Message<AppsFormBasedDetails>(Message.SUCCESS).buildResponse();
		} else {
			return new Message<AppsFormBasedDetails>(Message.FAIL).buildResponse();
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/update"}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> update(
			@RequestBody AppsFormBasedDetails formBasedDetails,
			@CurrentUser UserInfo currentUser) {
		logger.debug("-update  : {}" , formBasedDetails);
		transform(formBasedDetails);
		formBasedDetails.setInstId(currentUser.getInstId());
		if (formBasedDetailsService.update(formBasedDetails)
				&&appsService.updateApp(formBasedDetails)) {
		    return new Message<AppsFormBasedDetails>(Message.SUCCESS).buildResponse();
		} else {
			return new Message<AppsFormBasedDetails>(Message.FAIL).buildResponse();
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/delete"}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> delete(
			@RequestParam("ids") String ids,
			@CurrentUser UserInfo currentUser) {
		logger.debug("-delete  ids : {} " , ids);
		if (formBasedDetailsService.deleteBatch(ids)
				&& appsService.deleteBatch(ids)) {
			 return new Message<AppsFormBasedDetails>(Message.SUCCESS).buildResponse();
		} else {
			return new Message<AppsFormBasedDetails>(Message.FAIL).buildResponse();
		}
	}
	
}
