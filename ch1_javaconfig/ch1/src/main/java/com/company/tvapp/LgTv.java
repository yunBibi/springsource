package com.company.tvapp;

public class LgTv implements TV {
	
	//private SonySpeaker speaker = new SonySpeaker();
	private Speaker speaker;	// has-a : 초기화(생성자, setter)
	
	
	public LgTv() {
		System.out.println("LgTV 객체 생성");
	}
	
	public LgTv(Speaker speaker) {
		super();
		this.speaker = speaker;
	}	
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}	
	
	
	@Override
	public void turnOn() {
		System.out.println("LgTv - 전원 On");
	}
	@Override
	public void turnOff() {
		System.out.println("LgTv - 전원 Off");
	}
	@Override
	public void soundUp() {
		//System.out.println("LgTv - 볼륨 Up");
		//speaker = new SonySpeaker();
		speaker.volumeUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LgTv - 볼륨 Down");
		//speaker = new SonySpeaker();
		speaker.volumeDown();
	}
}




