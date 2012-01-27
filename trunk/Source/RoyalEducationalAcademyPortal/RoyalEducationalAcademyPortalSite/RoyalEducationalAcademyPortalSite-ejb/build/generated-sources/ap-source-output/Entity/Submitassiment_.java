package Entity;

import Entity.Markassiment;
import Entity.Members;
import Entity.Requestassiment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-27T15:15:34")
@StaticMetamodel(Submitassiment.class)
public class Submitassiment_ { 

    public static volatile SingularAttribute<Submitassiment, Requestassiment> raid;
    public static volatile SingularAttribute<Submitassiment, Integer> said;
    public static volatile ListAttribute<Submitassiment, Markassiment> markassimentList;
    public static volatile SingularAttribute<Submitassiment, String> safilename;
    public static volatile SingularAttribute<Submitassiment, Members> mid;

}