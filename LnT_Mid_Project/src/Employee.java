import java.util.ArrayList;

public class Employee {
    private String ID;
    private String Name;
    private String Gender;
    private String Position;
    private int Salary;
    private double Bonus;
    
    public void addBonus(ArrayList<Employee> employeeList, int counter) {
    	for (int i = 0; i < employeeList.size(); i++) {
			for (int j = i + 1; j < employeeList.size(); j++) {
				if (employeeList.get(j).getPosition().compareTo(employeeList.get(i).getPosition()) < 0) {
					Employee temp = employeeList.get(j);
					employeeList.set(j, employeeList.get(i));
					employeeList.set(i, temp);
				}
			}
		}
        if (counter % 3 == 1 && counter > 3) {
			for (int i = 0; i < employeeList.size()-1; i++) {
				int salary = employeeList.get(i).getSalary();
				double bonus = employeeList.get(i).getBonus();
				employeeList.get(i).setSalary((int)(salary + (salary * bonus)));
				 System.out.print(employeeList.get(i).getID());
				  if (i < employeeList.size()-2) {
				    System.out.print(", ");
				  }
				}
				System.out.println();
		}
        
        
    }

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		Salary = salary;
	}

	public double getBonus() {
		return Bonus;
	}

	public void setBonus(double bonus) {
		Bonus = bonus;
	}
}

   
    
    
    
	



