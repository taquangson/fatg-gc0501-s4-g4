package Entity;

import Entity.Class;
import Entity.Markassiment;
import Entity.Submitassiment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-30T23:23:01")
@StaticMetamodel(Requestassiment.class)
public class Requestassiment_ { 

    public static volatile SingularAttribute<Requestassiment, String> rainfo;
    public static volatile SingularAttribute<Requestassiment, Integer> raid;
    public static volatile ListAttribute<Requestassiment, Markassiment> markassimentList;
    public static volatile SingularAttribute<Requestassiment, String> raname;
    public static volatile SingularAttribute<Requestassiment, String> rafilename;
    public static volatile ListAttribute<Requestassiment, Submitassiment> submitassimentList;
    public static volatile SingularAttribute<Requestassiment, Class> cid;

}