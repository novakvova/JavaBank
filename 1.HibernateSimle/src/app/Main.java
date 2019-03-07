package app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

	private static SessionFactory factory=null;
	public static void main(String[] args) {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			factory = cfg.buildSessionFactory();
		} catch(Throwable ex) {
			
			System.err.println("Failed to create sessionFactory object." + ex);
		}
		Add("Петро");


	}
	private static void Add(String name) {
		Session session = factory.openSession();
		
		Student student2 = new Student();
		student2.setName(name);
		
		Transaction tx = session.beginTransaction();
		session.save(student2);
		
		tx.commit();
		System.out.println("Object saved successfully.....!!");
	}
	
//	public static void GetListStudent()
//	{
//		Session session=factory.openSession();
//		Transaction tx=null;
//		try
//		{
//			tx=session.beginTransaction();
//			
//			List list= session.createQuery("From Student").list();
//			for(Iterator it=list.iterator(); it.hasNext();)
//			{
//				Student st=(Student)it.next();
//				System.out.println("Id: "+st.getId());
//				System.out.println("Name: "+st.getName());
//				System.out.println("Phone: "+st.getPhone());
//				System.out.println("Degree: "+st.getDegree());
//				System.out.println("Roll: "+st.getRoll());
//			}
//			tx.commit();
//		}
//		catch(HibernateException e)
//		{
//			if(tx!=null) tx.rollback();
//			e.printStackTrace();
//		}
//		finally
//		{
//			session.close();
//		}
//	}
//	
//	public static void UpdateStudent(Student stud)
//	{
//		Session session=factory.openSession();
//		Transaction tx=null;
//		
//		try
//		{
//			tx=session.beginTransaction();
//			Student editStud=(Student)session.get(Student.class, stud.getId());
//			editStud.setName(stud.getName());
//			editStud.setDegree(stud.getDegree());
//			editStud.setPhone(stud.getPhone());
//			editStud.setRoll(stud.getRoll());
//			session.update(editStud);
//			tx.commit();
//		}
//		catch(HibernateException e)
//		{
//			if(tx!=null) tx.rollback();
//			e.printStackTrace();
//		}
//		finally
//		{
//			session.close();
//		}
//	}
//	public static void DeleteStudent(Long id)
//	{
//		Session session=factory.openSession();
//		Transaction tx=null;
//		try
//		{
//			tx=session.beginTransaction();
//			Student delStud=(Student)session.get(Student.class, id);
//			session.delete(delStud);
//			tx.commit();
//		}
//		catch(HibernateException e)
//		{
//			if(tx!=null) tx.rollback();
//			e.printStackTrace();
//		}
//		finally
//		{
//			session.close();
//		}
//	}

}
