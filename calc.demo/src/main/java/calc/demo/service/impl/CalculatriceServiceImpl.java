package calc.demo.service.impl;

import calc.demo.service.ICalculatriceService;

public class CalculatriceServiceImpl implements ICalculatriceService{

	@Override
	public int additionner(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int soustraire(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public int diviser(int a, int b) {
		// TODO Auto-generated method stub
		return a/b;
	}

	@Override
	public int multiplier(int a, int b) {
		// TODO Auto-generated method stub
		return a*b;
	}

}
