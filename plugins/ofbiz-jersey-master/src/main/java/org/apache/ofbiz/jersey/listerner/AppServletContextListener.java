/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.apache.ofbiz.jersey.listerner;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.webapp.WebAppUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.apache.ofbiz.jersey.util.ApiUtil.generateAdminToken;
import static org.apache.ofbiz.jersey.util.ApiUtil.invokeDelegator;

public class AppServletContextListener implements ServletContextListener {

	public static final String MODULE = AppServletContextListener.class.getName();

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		Delegator delegator = WebAppUtil.getDelegator(servletContext);
		LocalDispatcher dispatcher = WebAppUtil.getDispatcher(servletContext);
		Debug.logInfo("Jersey Context initialized, delegator " + delegator + ", dispatcher", MODULE);
		servletContext.setAttribute("delegator", delegator);
		servletContext.setAttribute("dispatcher", dispatcher);

		try {
			invokeDelegator(delegator);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("--------------------------------------- TOKEN ---------------------------------------------");
			System.out.println(generateAdminToken());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		Debug.logInfo("Jersey Context destroyed, removing delegator and dispatcher ", MODULE);
		context.removeAttribute("delegator");
		context.removeAttribute("dispatcher");
	}

}
