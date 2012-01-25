package Entity;

import Entity.Class;
import Entity.Course;
import Entity.Members;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-25T01:14:17")
@StaticMetamodel(Requestassiment.class)
public class Requestassiment_ { 

    public static volatile SingularAttribute<Requestassiment, String> rainfo;
    public static volatile SingularAttribute<Requestassiment, Integer> raid;
    public static volatile SingularAttribute<Requestassiment, String> raname;
    public static volatile SingularAttribute<Requestassiment, Date> radeadline;
    public static volatile SingularAttribute<Requestassiment, Members> stuffmid;
    public static volatile SingularAttribute<Requestassiment, String> rafilename;
    public static volatile SingularAttribute<Requestassiment, Course> cid2;
    public static volatile SingularAttribute<Requestassiment, Class> cid;
    public static volatile SingularAttribute<Requestassiment, Date> radate;

}