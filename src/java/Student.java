
public class Student {
	String sid;
	String tid;
	String fid;
	int order;
	public String getsec()
	{
		return sid;
	}
	public String getthird()
	{
		return tid;
	}
	public String getfour()
	{
		return fid;
	}
	public int getno()
	{
		return order;
	}
	public Student(String a,String b,String c)
	{
		this.sid=a;
		this.tid=b;
		this.fid=c;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
}
