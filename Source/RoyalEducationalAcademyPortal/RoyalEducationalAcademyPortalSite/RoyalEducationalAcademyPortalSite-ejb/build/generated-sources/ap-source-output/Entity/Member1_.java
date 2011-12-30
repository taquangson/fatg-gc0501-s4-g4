package Entity;

import Entity.Classmember;
import Entity.Feedback;
import Entity.Markassiment;
import Entity.Memberpermission;
import Entity.Submitassiment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-30T23:23:01")
@StaticMetamodel(Member1.class)
public class Member1_ { 

    public static volatile ListAttribute<Member1, Classmember> classmemberList;
    public static volatile SingularAttribute<Member1, String> mfullname;
    public static volatile SingularAttribute<Member1, Memberpermission> mpermission;
    public static volatile SingularAttribute<Member1, Date> mbirthdate;
    public static volatile SingularAttribute<Member1, String> musername;
    public static volatile SingularAttribute<Member1, String> memail;
    public static volatile ListAttribute<Member1, Markassiment> markassimentList;
    public static volatile SingularAttribute<Member1, String> madress;
    public static volatile SingularAttribute<Member1, byte[]> mavarta;
    public static volatile ListAttribute<Member1, Submitassiment> submitassimentList;
    public static volatile SingularAttribute<Member1, Integer> mid;
    public static volatile SingularAttribute<Member1, String> mpassword;
    public static volatile ListAttribute<Member1, Feedback> feedbackList;

}