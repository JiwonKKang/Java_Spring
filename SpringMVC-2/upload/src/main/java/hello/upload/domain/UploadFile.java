package hello.upload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;//사용자가 중복되는 이름을 사용할 수 있기때문에 고유한 이름을 만들어 저장
}
