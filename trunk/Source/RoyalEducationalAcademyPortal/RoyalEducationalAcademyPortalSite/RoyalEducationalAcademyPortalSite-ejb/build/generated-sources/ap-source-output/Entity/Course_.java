package Entity;

import Entity.Classincourse;
import Entity.Requestassiment;
import Entity.Sem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-27T14:44:45")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile ListAttribute<Course, Classincourse> classincourseList;
    public static volatile SingularAttribute<Course, Sem> sid;
    public static volatile ListAttribute<Course, Requestassiment> requestassimentList;
    public static volatile SingularAttribute<Course, Integer> cid;
    public static volatile SingularAttribute<Course, String> cname;

}