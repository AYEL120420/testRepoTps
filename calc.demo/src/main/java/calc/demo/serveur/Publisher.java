package calc.demo.serveur;

import calc.demo.service.impl.CalculatriceServiceImpl;

public class Publisher {

	public static void main(String[] args) {
		
		CalculatriceServiceImpl calService = new CalculatriceServiceImpl(); 
		
		int result = calService.additionner(10, 50);
		System.out.println(result);
		
	//	Endpoint.publish
	}

}
