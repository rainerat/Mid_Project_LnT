import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	Scanner scan = new Scanner(System.in);
	ArrayList<Employee> employeeList = new ArrayList<>();
	
	public Main() {
		
		int choose = 0;
		do {
		System.out.println(
				  "   _____ _____    _____ _____ __ __ _____ _____ _____ \n"
				+ "  |  _  |_   _|  |     |   __|  |  |     |     |     |\n"
				+ "  |   __| | |    | | | |   __|-   -|-   -|   --|  |  |\n"
				+ "  |__|    |_|    |_|_|_|_____|__|__|_____|_____|_____|\n"
				+ "                                                      ");
		System.out.println("1. Insert employee data");
		System.out.println("2. View employee data");
		System.out.println("3. Update employee data");
		System.out.println("4. Delete employee data");
		System.out.println("5. Exit");
		System.out.print(">> "); 
		try {
			choose = scan.nextInt();
		} catch (Exception e) {
		}
		scan.nextLine();
		switch (choose) {
		case 1:
			insert();
			break;
		case 2:
			if (employeeList.isEmpty()) {
				System.out.println("NO DATA AVAILABLE\nENTER to return"); scan.nextLine();

			} else {
				view();
				System.out.println("ENTER to return"); scan.nextLine();
			}
			break;
		case 3:
			if (employeeList.isEmpty()) {
				System.out.println("NO DATA AVAILABLE\nENTER to return"); scan.nextLine();
			} else {
				update();
			}
			break;
		case 4:
			if (employeeList.isEmpty()) {
				System.out.println("NO DATA AVAILABLE\nENTER to return"); scan.nextLine();
			} else {
				remove();
			}
			break;
		default:
			break;
		}
		} while (choose != 5);
	}
	
	public void insert() {
		
		Random rand = new Random();
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String mm = "";
		String xxxx = "";
		for (int i = 0; i < 2; i++) {
			mm += letters.charAt(rand.nextInt(26));
		}
		for (int i = 0; i < 4; i++) {
			xxxx += rand.nextInt(9);
		}
		String tID = mm + "-" + xxxx;
		
		String tName = "";
		while(true) {
			System.out.print("Input nama karyawan [>=3]: "); tName = scan.nextLine();
			if (tName.length() >=3 && tName.matches("^[ a-zA-Z]+$")) break;
		}
		String tGender = "";
		while(true) {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): "); tGender = scan.nextLine();
			if (tGender.equals("Laki-laki") || tGender.equals("Perempuan")) break;
		}
		String tPosition = "";
		while(true) {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): "); tPosition = scan.nextLine();
			if (tPosition.equals("Manager") || tPosition.equals("Supervisor") || tPosition.equals("Admin")) break;
		}
		
		Employee employee = new Employee();
	    employee.setID(tID);
	    employee.setName(tName);
	    employee.setGender(tGender);
	    employee.setPosition(tPosition);
	    
	    if (tPosition.equals("Manager")) {
	        employee.setSalary(8000000);
	        employee.setBonus(0.1);
	    } else if (tPosition.equals("Supervisor")) {
	        employee.setSalary(6000000);
	        employee.setBonus(0.075);
	    } else if (tPosition.equals("Admin")) {
	        employee.setSalary(4000000);
	        employee.setBonus(0.05);
	    }
	    employeeList.add(employee);
	    
	    int managerCount = 0;
	    int supervisorCount = 0;
	    int adminCount = 0;
	    
	    for (int i = 0; i < employeeList.size(); i++) {
	        if (employeeList.get(i).getPosition().equals("Manager")) {
	            managerCount++;
	        } else if (employeeList.get(i).getPosition().equals("Supervisor")) {
	            supervisorCount++;
	        } else if (employeeList.get(i).getPosition().equals("Admin")) {
	            adminCount++;
	        }
	    }
	    
	    if (managerCount % 3 == 1 && managerCount > 3) {
	    	System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
	    	employee.addBonus(employeeList, managerCount);
		} else if (supervisorCount % 3 == 1 && supervisorCount > 3) {
			System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
	    	employee.addBonus(employeeList, supervisorCount);
		} else if (adminCount % 3 == 1 && adminCount > 3) {
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
	    	employee.addBonus(employeeList, adminCount);
		}
	    System.out.println("Berhasil menambahkan karyawan dengan id " + tID);
	    
	    System.out.print("ENTER to return"); scan.nextLine();
	}
	
	public void view() {
		for (int i = 0; i < employeeList.size(); i++) {
			for (int j = i + 1; j < employeeList.size(); j++) {
				if (employeeList.get(j).getName().compareTo(employeeList.get(i).getName()) < 0) {
					Employee temp = employeeList.get(j);
					employeeList.set(j, employeeList.get(i));
					employeeList.set(i, temp);
				}
			}
		}
		System.out.println("|----|-------------------|------------------------------------|-------------------|-------------------|-------------------|");
		System.out.printf("| %-2s | %-17s | %-34s | %-17s | %-17s | %-17s |\n" , "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.println("|----|-------------------|------------------------------------|-------------------|-------------------|-------------------|");
		
		for (int i = 0; i < employeeList.size(); i++) {
			System.out.printf("| %2s | %17s | %34s | %17s | %17s | %17s |\n" ,
					i+1, employeeList.get(i).getID(), 
					employeeList.get(i).getName(), 
					employeeList.get(i).getGender(), 
					employeeList.get(i).getPosition(), 
					employeeList.get(i).getSalary());
		}
		System.out.println("|----|-------------------|------------------------------------|-------------------|-------------------|-------------------|");
	}

	public void update() {
		view();
		int choose = 0;
		while (true) {
		System.out.print("Input nomor urutan karyawan yang ingin diupdate: "); choose = scan.nextInt(); scan.nextLine();
		if (choose <= employeeList.size() && choose > 0) break;
		}
		String nName = "";
		while (true) {
			System.out.print("Input nama karyawan [>=3]: "); nName = scan.nextLine();
			if ((nName.length() >=3 || nName.equals("0")) && nName.matches("^[ a-zA-Z0-9]+$")) break;
		}
		String nGender = "";
		while(true) {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): "); nGender = scan.nextLine();
			if (nGender.equals("Laki-laki") || nGender.equals("Perempuan") || nGender.equals("0")) break;
		}
		String nPosition = "";
		while(true) {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): "); nPosition = scan.nextLine();
			if (nPosition.equals("Manager") || nPosition.equals("Supervisor") || nPosition.equals("Admin") || nPosition.equals("0")) break;
		}
		
		Employee employee = employeeList.get(choose-1);
		
		if (nName.equals("0")) {
			employee.setName(employee.getName());
		} else {
			employee.setName(nName);
		}
		if (nGender.equals("0")) {
			employee.setGender(employee.getGender());
		} else {
			employee.setGender(nGender);
		}
		if (nPosition.equals("0")) {
			employee.setPosition(employee.getPosition());
		} else if (nPosition.equals("Manager")) {
			employee.setPosition(nPosition);
	        employee.setSalary(8000000);
	        employee.setBonus(0.1);
	    } else if (nPosition.equals("Supervisor")) {
	    	employee.setPosition(nPosition);
	        employee.setSalary(6000000);
	        employee.setBonus(0.075);
	    } else if (nPosition.equals("Admin")) {
	    	employee.setPosition(nPosition);
	        employee.setSalary(4000000);
	        employee.setBonus(0.05);
	    }
		
		System.out.println("Berhasil mengupdate karyawan dengan id " + employeeList.get(choose-1).getID());
		System.out.println("ENTER to return"); scan.nextLine();
	}
	
	public void remove() {
		view();
		int choose = 0;
		while (true) {
			System.out.print("Input nomor urutan yang ingin dihapus: "); choose = scan.nextInt(); scan.nextLine();
			if (choose <= employeeList.size() && choose >= 0) {
				if (choose == 0) {
					return;
				} else if (choose > 0) {
					System.out.println("Karyawan dengan kode " + employeeList.get(choose-1).getID() + " berhasil dihapus");
					employeeList.remove(choose-1);
					System.out.println("ENTER to return"); scan.nextLine();
					break;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
}


