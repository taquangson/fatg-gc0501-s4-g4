package Entity;

import Entity.Member1;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-30T23:23:01")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Integer> fid;
    public static volatile SingularAttribute<Feedback, String> fquestion;
    public static volatile SingularAttribute<Feedback, Member1> mid;

}