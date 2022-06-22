# mileage-task

## 트리플 사전과제
***트리플여행자 클럽 마일리지 서비스를 위한 코드입니다***

## 목적
***트리플 사용자들이 장소에 대한 리뷰를 작성할 때 포인트를 부여***
***전체/개인에 대한 포인트 부여 히스토리와 개인별 누적 포인트를 관리***

## 기능
1. 유저 개인의 누적 포인트와 포인트 증감 이력확인 기능
2. 리뷰 작성시 내용점수와 첫 리뷰 보너스 점수 적립
3. 리뷰 수정시 수정된 내용에 맞게 내용점수와 보너스점수 증감
4. 리뷰 삭제시 내용점수와 보너스점수에 따른 누적 점수 감소

## 스택  
- Java 11
- SpringBoot - 2.7.4
- Gradle - 7.4.1
- JPA
- Spring Data Jpa
- MySQL 8.0.27
- h2(Memory)
- IntelliJ

## 트러블 슈팅
- '첫 리뷰 보너스'를 위한 로직을 작성하기전에 어떻게 리뷰가 달려있는지 없는지 확인할까에 집중했다. 보너스 점수는 말그대로 해당 장소(상품)에 첫 번째로 달리는 리뷰에 보너스 점수를 주는 것인데 이것을 
   1. Place에 첫 리뷰를 구분 짓는 타입을 주어서 구분해야하나 
   2. Review테이블에 placeId를 exist로 찾아야하나 고민 했었다.

처음엔 1번 방법이 효율적이고 빠른 방법이라 생각했다. 왜냐하면 2번 방법은 exist 쿼리를 날려서 한번 더 확인해야하고, 1번 방법은 포인트를 주기전에 Entity를 이미 찾아 놨기때문에 Entity의 status 값만 확인 하면 되어서 더 좋다고 생각 했지만. 장소가 리뷰에 대해서 안다는 것이 문제가 될 수 있을것 같았기 때문이다. 1번 방법은 리뷰가 추가되고 삭제 될때마다 place의 첫 리뷰 여부가 업데이트 되어야한다. 리뷰의 변화가 상품까지 영향을 끼치는 것으로 만약 리뷰평점도 place의 필드로 추가 된다거나 하면 place의 크기가 점점 커질 것이기 때문에 1번이 아닌 2번 방법으로 보너스 점수를 처리했다. 

## 아쉬운 점
***포인트 부여의 비즈니스 로직이 아쉬웠다. If와 Else로 딱딱한 로직을 쓰지 않고 더 나은 방법을 찾고자 했으나 포인트 부여에 대한 Domain 설계를 다른 방식으로 접근 해야할지 또 다른 방법이 있을지에 대해서 고민하는 시간이 많이 길었던것 같다. SOLID를 준수하여 개발 하고자 했으나 어떻게 요구사항에 맞게 포인트 증감을 시킬지 조금 더 고민을 해봐야 할것 같다.***

## 실행
### [DDL 파일](https://github.com/Allaccept12/mileage-task/blob/main/src/main/resources/schema.sql)
### [API 문서](https://melted-magician-d9c.notion.site/API-6690238ee6fc45bab4cdfc966a3925af)



