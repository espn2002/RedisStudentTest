package com.redisstudent.bean;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Student {
	 private Long id;
	    private Date birthday;
	    private Integer avgscore;
	    private String description;
	    private String name;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Date getBirthday() {
	        return birthday;
	    }

	    public void setBirthday(Date birthday) {
	        this.birthday = birthday;
	    }

	    public Integer getAvgscore() {
	        return avgscore;
	    }

	    public void setAvgscore(Integer avgscore) {
	        this.avgscore = avgscore;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    //把map转化为student
	    public void fromMap(Map map) throws ParseException {
	        id = Long.parseLong((String) map.get("id"));
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        birthday = new Date(simpleDateFormat.parse((String) map.get("birthday")).getTime());
	        avgscore = Integer.parseInt((String) map.get("avgscore"));
	        description = (String) map.get("description");
	        name = (String) map.get("name");
	    }
	    //把student转化为map
	    public Map<String, String> toMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("id", id.toString());
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        map.put("birthday", sdf.format(birthday));
	        System.out.println(sdf.format(birthday));			
	        map.put("avgscore", avgscore.toString());
	        map.put("description", description);
	        map.put("name", name);
	        return map;
	    }

		
}