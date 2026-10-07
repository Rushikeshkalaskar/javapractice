class DeployEmployee 
{
   public boolean isAddNewEmployee(Employee employee)
   {
        if(employee.getAge()>18)
		{  System.out.println("Employee added successully with salary  "+employee.getSal());
		}
		else{
		  ChildEmployeeNotAllowedException ex=new ChildEmployeeNotAllowedException("Employee age should greater than 18");
		  throw ex;
		}
   }
}
