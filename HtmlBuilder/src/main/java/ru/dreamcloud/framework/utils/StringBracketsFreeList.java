package ru.dreamcloud.framework.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class StringBracketsFreeList<E> extends ArrayList<E>{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext()){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (;;) {
            E e = it.next(); 
            sb.append(e);
            if (!it.hasNext()){            
                return sb.toString();
            }
            sb.append(" ");
        }		
	}

}
