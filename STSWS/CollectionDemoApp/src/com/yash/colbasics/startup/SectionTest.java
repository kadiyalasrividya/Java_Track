package com.yash.colbasics.startup;

import java.util.Iterator;
import java.util.LinkedList;

import com.yash.colbasics.pojo.Section;
import com.yash.colbasics.service.SectionService;

public class SectionTest {

	public static void main(String[] args) {
		SectionService sectionService=new SectionService();
		LinkedList<Section> sections=sectionService.getAllSections();
		
		Iterator<Section> secIterator;
		secIterator=sections.iterator();
		
		while(secIterator.hasNext()){
			Section section=secIterator.next();
			System.out.println(section.getId()+", "+section.getName()+", "+section.getCreatedBy());
		}
		
		sectionService.addSection(new Section(3, "HTML", 101));
		System.out.println("After adding new section");
		secIterator=sections.iterator();
		
		while(secIterator.hasNext()){
			Section section=secIterator.next();
			System.out.println(section.getId()+", "+section.getName()+", "+section.getCreatedBy());
		}
		
		
	}

}
