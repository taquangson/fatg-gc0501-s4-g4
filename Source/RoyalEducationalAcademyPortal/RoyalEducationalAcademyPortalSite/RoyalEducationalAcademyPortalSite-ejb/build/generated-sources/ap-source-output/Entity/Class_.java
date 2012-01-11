package Entity;

import Entity.Classincourse;
import Entity.Classmember;
import Entity.Requestassiment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-11T18:12:32")
@StaticMetamodel(Class.class)
public class Class_ { 

    public static volatile ListAttribute<Class, Classincourse> classincourseList;
    public static volatile ListAttribute<Class, Classmember> classmemberList;
    public static volatile SingularAttribute<Class, Integer> cid;
    public static volatile ListAttribute<Class, Requestassiment> requestassimentList;
    public static volatile SingularAttribute<Class, String> cname;

}