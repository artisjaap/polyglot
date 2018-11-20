package be.artisjaap.document.api.brieflocatie;

public class BriefLocatieFactory {

    public static BriefOpslagLocatie voorDB(){
        return new BriefOpslagDB();
    }

    public static BriefOpslagLocatie voorAbsolutePath(String path){

        return AbsolutePathLocation.newBuilder().withPath(path).build();
    }

    public static BriefOpslagLocatie voorRelatiefPath(String path){
        return RelativePathLocation.newBuilder().withPath(path).build();
    }

    public static BriefOpslagLocatie briefNietOpslaan(){
        return new BriefOpslagNietOpslaan();
    }


}
