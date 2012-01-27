package Entity;

import Entity.Members;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-27T15:15:34")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, String> fbquestion;
    public static volatile SingularAttribute<Feedback, Integer> fbid;
    public static volatile SingularAttribute<Feedback, Date> fbdate;
    public static volatile SingularAttribute<Feedback, Members> mid;

}