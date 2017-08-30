package com.javafinal;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class App 
{
    private static final boolean CLEAR_SCREEN = true;
    static Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	Integer mainOption = null;
        do {
            System.out.print("Ingrese:\n"
            		+ "1 - Ingresar cliente\n"
            		+ "2 - Ingresar auto\n"
            		+ "3 - Ingresar alquiler\n"
            		+ "4 - Buscar cliente\n"
            		+ "5 - Buscar auto\n"
            		+ "6 - Buscar alquiler\n"
            		+ "0 - Salir\n"
            		+ "Opcion: ");
            mainOption = scanner.nextInt();
            if (mainOption != 0){
            	switch (mainOption) {
            	case 1:
            		inputCustomer();
            		break;
            	case 2:
            		inputCar();
            		break;
            	case 3:
            		inputRent();
            		break;
            	case 4:
            		searchCustomer();
            		break;
            	case 5:
            		searchCar();
            		break;
            	case 6:
            		searchRent();
            		break;
            	default:
            		System.out.println("Opcion no valida");
            	}
            }
        } while (mainOption != 0);
    }

    private static void inputCustomer() {
		Integer again = null;
		do {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        System.out.print("Ingrese su dni: ");
                        String dni = scanner.next();
                        System.out.print("Ingrese su nombre: ");
                        String name = scanner.next();
                        System.out.print("Ingrese su cumpleaños: ");
                        String date = scanner.next();
                        Date birthday = null;
                        try
                        {
                            birthday = format.parse(date);
                        }
                        catch(ParseException e)
                        {
                            System.out.println("error: " + e.getMessage());
                            birthday = new Date();
                        }
                        System.out.print("Ingrese su telefono: ");
                        String phone = scanner.next();
                        Customer newCustomer = Customer.createNew(dni, name, birthday, phone);
                        
			again = shouldInputAgain();
		} while (again != 0);
	}
    
    private static void inputCar() {
		Integer again = null;
		do {
			System.out.print("Ingrese el dominio: ");
                        String dominio = scanner.next();
                        System.out.print("Ingrese el color: ");
                        String color = scanner.next();
                        
                        Car car = Car.createNew(dominio, 4, color, "1lt", "13km", Double.valueOf("13"), 10);
                        
			again = shouldInputAgain();
		} while (again != 0);
	}
    
    private static void inputRent() {
		Integer again = null;
		do {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.print("Ingrese la fecha desde: ");
                        String date = scanner.next();
                        Date desde = null;
                        try
                        {
                            desde = format.parse(date);
                        }
                        catch(ParseException e)
                        {
                            System.out.println("error: " + e.getMessage());
                        }
                        
                        System.out.print("Ingrese cuantos dias renta: ");
                        int dias = scanner.nextInt();
                        
                        System.out.print("Ingrese dni cliente: ");
                        String dni = scanner.next();
                        
                        System.out.print("Ingrese dominio auto: ");
                        String dominio = scanner.next();
                        
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(desde);
                        cal.add(Calendar.DATE, dias);
                        
                        Date hasta = cal.getTime();
                        
                        Rent.createNew(dni, dominio, desde, hasta);
                        
			again = shouldInputAgain();
		} while (again != 0);
	}
    
    private static void searchCustomer() {
    	Integer again = null;
		do {
			System.out.print("Ingrese el atributo por el cual buscar (nombre, edad, dni): ");
            String atributo = scanner.nextLine();
            System.out.print("Ingrese el valor que debe tener: ");
            String valor = scanner.nextLine();
            Customer customer = Customer.load(valor);
            if(customer != null)
            {
                System.out.print("encontrado: ");
                System.out.println(customer.getName());
			again = shouldSearchAgain();
			if (again == 2) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                            System.out.print("Ingrese su nombre: ");
                            String name = scanner.next();
                            System.out.print("Ingrese su cumpleaños: ");
                            String date = scanner.next();
                            Date birthday = null;
                            try
                            {
                                birthday = format.parse(date);
                            }
                            catch(ParseException e)
                            {
                                System.out.println("error: " + e.getMessage());
                                birthday = new Date();
                            }
                            System.out.print("Ingrese su telefono: ");
                            String phone = scanner.next();
                            
                            customer.setBirthday(birthday);
                            customer.setName(name);
                            customer.setPhone(phone);
                            customer.save();
			}
            }
               else
                    System.out.print("No hay resultados.");
		} while (again != 0);
	}
    
    private static void searchCar() {
    	Integer again = null;
		do {
			System.out.print("Ingrese el atributo por el cual buscar (dominio, puertas, color, combustible, km, equipaje, pasajeros): ");
            String atributo = scanner.nextLine();
            System.out.print("Ingrese el valor que debe tener: ");
            String valor = scanner.nextLine();
            
            Car car = Car.load(valor);
            if(car != null)    
            {
                System.out.println("Encontrado!");
                System.out.format("Domain: %s%n", car.getDomain());
                System.out.format("Color: %s%n", car.getColor());
            
            again = shouldSearchAgain();
                if (again == 2) {
                            System.out.print("Ingrese el color: ");
                            String color = scanner.next();
                            car.setColor(color);
                            car.save();
			}
            }
            else
            {
                System.out.println("no hay resultados.");
            }
		} while (again != 0);
                
	}
    
    private static void searchRent() {
    	Integer again = null;
		do {
            System.out.print("Ingrese el DNI del cliente: ");
            String documento = scanner.next();
            
            List<Rent> rents = Rent.search(documento);
            
            
            if(rents != null)
            {
                System.out.println("- Encontrados para el cliente "+ documento + " -");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                for(Rent item : rents)
                {
                    System.out.format("[Id => "+ item.getIdRent() +"] El cliente rento el auto %s el dia %s%n", item.getCar(), format.format(item.getDateFrom()));
                }
            
            
            again = shouldSearchAgain();
            if (again == 2) {
                    
                    System.out.println("Ingrese el id de la renta a editar: ");
                    Integer id = scanner.nextInt();
                    
                    Rent rent = Rent.load(id);
                    
                    if(rent != null)
                    {
                        
			System.out.print("Ingrese la fecha desde: ");
                        String date = scanner.next();
                        Date desde = null;
                        try
                        {
                            desde = format.parse(date);
                        }
                        catch(ParseException e)
                        {
                            System.out.println("error: " + e.getMessage());
                        }
                        
                        System.out.print("Ingrese cuantos dias renta: ");
                        int dias = scanner.nextInt();
                        
                        System.out.print("Ingrese dni cliente: ");
                        String dni = scanner.next();
                        
                        System.out.print("Ingrese dominio auto: ");
                        String dominioNew = scanner.next();
                        
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(desde);
                        cal.add(Calendar.DATE, dias);
                        
                        Date hasta = cal.getTime();
                        
                        rent.setCar(dominioNew);
                        rent.setCustomer(dni);
                        rent.setDateFrom(desde);
                        rent.setDateTo(hasta);
                        rent.save();
                    }
                    else
                    {
                        System.out.println("ese id no existe!");
                    }
                    
		}
            }
            else
            {
                System.out.println("No hay resultados.");
            }
		} while (again != 0);
	}
    
    private static Integer shouldInputAgain() {
    	System.out.print("Ingresar otro?\n"
				+ "1 - Si\n"
        		+ "0 - No, volver atrás\n"
        		+ "Opcion + Enter: ");
        return scanner.nextInt();
    }
    
    private static Integer shouldSearchAgain() {
    	System.out.print("Buscar otro?\n"
				+ "1 - Si\n"
				+ "2 - No, actualizar datos\n"
        		+ "0 - No, volver atrás\n"
        		+ "Opcion + Enter: ");
        return scanner.nextInt();
    }
}
