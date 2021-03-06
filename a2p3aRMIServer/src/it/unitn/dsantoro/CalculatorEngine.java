/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.dsantoro;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author acca
 */
public class CalculatorEngine implements Calculator {

    public CalculatorEngine() throws RemoteException {
        super();
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.getProperties().put("java.security.policy","java.policy");
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Calculator";
            Calculator calculator = new CalculatorEngine();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculator, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, stub);
            System.out.println("CalculatorEngine bound");
        } catch (Exception e) {
            System.err.println("CalculatorEngine exception:");
            e.printStackTrace();
        }
    }   

    @Override
    public int sum(int add1, int add2) throws RemoteException {
        return add1 + add2;
    }

    @Override
    public double bmi(int weight, int height) throws RemoteException {        
        double w = (double)weight;
        double h = (double)height;
        double bmi = ( w /((h*h)/10000) );
        System.out.println(bmi);
        return bmi;
    }
}
