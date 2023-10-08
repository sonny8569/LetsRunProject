# Let's Run Project 
## OS :  Android OS 
## Native API : GPS 
## Server : Firebase RealTime Database 
## Design Pattern : MVVM 
## Language : Kotlin 

> 안녕하세요 Android 개발자 Sonny8569 입니다 
>> 해당 Project 은 제가 F-Lab을 수강할 당시 만든 프로젝트 입니다 
>> Android Native API 은 GPS 을 사용했습니다 
>>멘토님은 Nathan 님이시며 멘토링 바탕으로 제가 개발을 했습니다 
> 해당 Project 은 Clean Architecture 바탕으로 만들었습니다 
> app은 Firebase Realtime Database 에 조회 하는 기능을 담당 합니다
> Device 은 GPS 기반으로 위도 경도 값을 계산하여 거리 값을 계산합니다
> Database 은 로그인 정보 혹은 거리 값을 저장합니다 
> Controller 은 Device 와 Database 밑에 있습니다 주로 app 에서 Device 와 Database을 호출할떄 씁니다 
> domain 은 데이터를 요청할때 씁니다 app -> domain -> Controller 
> 하지만 의존성 역전을 방지하기 위해 domain 은 Controller 을 호출할떄 interface 을 사용하여 Controller을 작동합니다 
> 이는 Controller 또한 database 와  device 또한 마찬가지 입니다 


![img](CleanArchitecture.jpg)
