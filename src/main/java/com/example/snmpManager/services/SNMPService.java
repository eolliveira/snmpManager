package com.example.snmpManager.services;


import org.springframework.stereotype.Service;

import com.example.snmpManager.model.ManagedDevice;

@Service
public class SNMPService {
	

	///busca dispositivos com base um faixa de ips definidos
	 public void discoverButtonActionPerformed() {//GEN-FIRST:event_discoverButtonActionPerformed
	    	//verifica se ip begin and end � valido 
	        if(ManagedDevice.isIpValid("192.168.0.1")) {
	            if(ManagedDevice.isIpValid("192.168.0.254")) {
	                int[] begin = new int[4];
	                int[] end = new int[4];

	                //String[] result = rangeBeginTextField.getText().split("\\.");
	                String[] result = {"10","0","5","1"};
	                for(int i = 0; i < 4; i++) {
	                    begin[i] = Integer.parseInt(result[i]);
	                }

	                //result = rangeEndTextField.getText().split("\\.");
	                String[] result2 = {"10","0","5","254"};
	                for(int i = 0; i < 4; i++) {
	                    end[i] = Integer.parseInt(result2[i]);
	                }
	               
	                if(begin[0] != end[0] || begin[1] != end[1] || begin[2] != end[2]) {
	                    //JOptionPane.showMessageDialog(this, "Range too big!");
	                    System.out.println("Range too big!");
	                    //rangeEndTextField.requestFocus();"
	                } else {
	                    if(begin[3] > end[3]) {
	                        int temp = begin[3];
	                        begin[3] = end[3];
	                        end[3] = temp;
	                    }
	                    SNMPMessenger.discoverDevices(begin, end);
	                }
	            } else {
	                //JOptionPane.showMessageDialog(this, "Invalid IP address!");
	            	System.out.println("Invalid IP address!");
	                //rangeEndTextField.requestFocus();
	            }
	        } else {
	            //JOptionPane.showMessageDialog(this, "Invalid IP address!");
	        	System.out.println("Invalid IP address!");
	            //rangeBeginTextField.requestFocus();
	        }
	    }//GEN-LAST:event_discoverButtonActionPerformed

}
