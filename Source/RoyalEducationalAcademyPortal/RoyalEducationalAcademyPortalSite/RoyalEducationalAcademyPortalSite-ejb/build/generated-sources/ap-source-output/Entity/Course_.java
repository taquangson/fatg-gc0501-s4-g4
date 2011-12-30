package Entity;

import Entity.Classincourse;
import Entity.Sem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-30T23:23:01")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile ListAttribute<Course, Classincourse> classincourseList;
    public static volatile SingularAttribute<Course, Sem> sid;
    public static volatile SingularAttribute<Course, Integer> cid;
    public static volatile SingularAttribute<Course, String> cname;

}