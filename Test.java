package com.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.bean.Product;

@WebServlet("/Product")
public class Test extends HttpServlet{

	SessionFactory sessfact;
	
	@Override
	public void init() throws ServletException {
		AnnotationConfiguration config=new AnnotationConfiguration();
        config.configure("com/resources/hibernate.cfg.xml");	
        sessfact=config.buildSessionFactory();
        System.out.println("Configuration started");
        
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("start service method");
		
		String name = req.getParameter("p_name");
		String p_categiry=req.getParameter("p_categiry");
		String price= req.getParameter("price");
		String quantity=req.getParameter("quantity");
		
		System.out.println("geting value in java : "+name+", "+p_categiry+", "+price+","+quantity);
		
		int price1 = Integer.valueOf(price);
		int  quantity1= Integer.valueOf(quantity);
		
		 Session session = sessfact.openSession();
		 
		 Product setProduct= new Product();
		 
		 setProduct.setP_name(name);
		 setProduct.setP_categiry(p_categiry);
		 setProduct.setPrice(price1);
		 setProduct.setQty(quantity1);
		 
		
		 session.save(setProduct);
		 
		 Transaction tx = session.beginTransaction();
		 tx.commit();
		 
		 System.out.println("Done");
	
	}
}
