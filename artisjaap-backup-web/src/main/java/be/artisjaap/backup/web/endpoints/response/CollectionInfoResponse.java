package be.artisjaap.backup.web.endpoints.response;

import be.artisjaap.backup.action.to.CollectionInfoTO;

public class CollectionInfoResponse {
    private String collectionName;
    private Long size;
    private Boolean capped;

    public static CollectionInfoResponse from(CollectionInfoTO collectionInfo){
        CollectionInfoResponse info = new CollectionInfoResponse();
        info.collectionName = collectionInfo.getCollectionName();
        info.capped = collectionInfo.getCapped();
        info.size = collectionInfo.getSize();

        return info;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean getCapped() {
        return capped;
    }

    public void setCapped(Boolean capped) {
        this.capped = capped;
    }
}
