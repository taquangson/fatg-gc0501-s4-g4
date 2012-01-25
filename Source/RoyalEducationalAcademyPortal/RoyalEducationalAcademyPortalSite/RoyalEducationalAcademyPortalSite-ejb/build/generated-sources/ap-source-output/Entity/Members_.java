package Entity;

import Entity.Memberpermission;
import Entity.Requestassiment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-25T01:14:17")
@StaticMetamodel(Members.class)
public class Members_ { 

    public static volatile SingularAttribute<Members, Date> mbirthdate;
    public static volatile SingularAttribute<Members, String> musername;
    public static volatile SingularAttribute<Members, String> memail;
    public static volatile SingularAttribute<Members, String> mfullname;
    public static volatile SingularAttribute<Members, String> madress;
    public static volatile SingularAttribute<Members, byte[]> mavarta;
    public static volatile SingularAttribute<Members, Integer> mid;
    public static volatile SingularAttribute<Members, String> mpassword;
    public static volatile ListAttribute<Members, Requestassiment> requestassimentList;
    public static volatile SingularAttribute<Members, Memberpermission> mpermission;

}