package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.Customer;
@WebServlet("/CustomerApi")
public class CustomerApi extends HttpServlet {
	
	
		private static final long serialVersionUID = 1L;
		
		Customer customerObj = new Customer();

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.doGet(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			String output = customerObj.insertCustomer(
					request.getParameter("customerType"),
					request.getParameter("customerName"),
					request.getParameter("addedDate"),
					request.getParameter("email"),
					request.getParameter("password")

					);
			response.getWriter().write(output);
		}

		@Override
		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			Map<String, String> paras = getParasMap(request);
			String output = customerObj.updateCustomer(paras.get("customerId").toString(),
					paras.get("customerType").toString(),
					paras.get("customerName").toString(),
					paras.get("addedDate").toString(),
					paras.get("password").toString(),
					paras.get("email").toString());
			
			response.getWriter().write(output);
		} 

		@Override
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			Map<String, String> paras = getParasMap(request);
			String output = customerObj.deleteCustomer(paras.get("customerId").toString());
			response.getWriter().write(output);
		}

		private static Map<String, String> getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
						scanner.useDelimiter("\\A").next() : "";
						scanner.close();
						String[] params = queryString.split("&");
						for (String param : params)
						{ 
							String[] p = param.split("=");
							map.put(p[0], p[1]);
						}
			}
			catch (Exception e)
			{
			}
			return map;
		}

}
