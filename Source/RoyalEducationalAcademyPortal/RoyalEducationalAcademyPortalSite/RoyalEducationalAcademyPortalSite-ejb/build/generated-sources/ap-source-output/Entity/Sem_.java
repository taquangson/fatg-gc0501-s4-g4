package Entity;

import Entity.Bath;
import Entity.Course;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-11T18:12:32")
@StaticMetamodel(Sem.class)
public class Sem_ { 

    public static volatile SingularAttribute<Sem, Integer> sid;
    public static volatile ListAttribute<Sem, Course> courseList;
    public static volatile SingularAttribute<Sem, String> sname;
    public static volatile SingularAttribute<Sem, Bath> bid;

}