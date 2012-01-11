package Entity;

import Entity.Classincourse;
import Entity.Sem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-11T18:12:32")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile ListAttribute<Course, Classincourse> classincourseList;
    public static volatile SingularAttribute<Course, Sem> sid;
    public static volatile SingularAttribute<Course, Integer> cid;
    public static volatile SingularAttribute<Course, String> cname;

}