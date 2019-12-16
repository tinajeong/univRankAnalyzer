# univRankAnalyzer

> 부제: 사실은 되게 유용한 자바 프로젝트  

  

자바를 막 배운 코린이로서 자바 어플리케이션을 개발할 때마다 들었던 의문이 있습니다.  

  

- 자바로 개발할 때 일반적으로 사용하는 기술들에는 뭐가 있지?  
- 이 개발자 분은 어떻게 정확한 사용법을 찾았지?  
- 코드는 어떻게 관리해야 하지? 잘 관리할 수 있는 방법이 뭘까?  
- 다른 개발자들과 소통이 잘 되는 코드를 만들기 위해서는 어떻게 해야하지?  
- 내가 만들고 싶은 어플리케이션이 생기면 어디에서부터 시작해야 하지? 
- 등등..



이 질문들에 대한 답을 주는 도움들이 있어왔습니다.  

제 답이 완벽한 답이라고 할 순 없고, 지금도 계속 변화해가는 중이지만,

어디에서부터 시작해야될지 몰라서 무작정 해나갔던 시간들을 도울 순 있을 것 같습니다. 

자바 프로젝트 개발을 해나가는 과정에 대해 적었습니다. 이 프로젝트가 시작점이 됐으면 좋겠습니다 :) 

# Prerequisite

**JDK**

컴퓨터에 1.8 버전 이상이 설치되어 있어야 합니다. 터미널에서 java -version을 쳐보고 없다면 [oracle](https://www.oracle.com/technetwork/java/javase/downloads/index.html)에서 JDK를 다운로드 하세요.

**IntelliJ Ultimate Version**

Eclipse나 IntelliJ Community 버전도 가능하지만, ultimate version 기준으로 문서를 작성하였음을 알려드립니다.

학생은 1년간 무료로 사용할 수 있습니다.[관련포스팅](https://whitepaek.tistory.com/6)을 참고하세요.

**Git**

git이 설치 되어서 bash shell을 이용할 수 있어야 합니다. 없다면 https://git-scm.com/ 에서 다운로드 받으세요.

**비판적 사고**

이 프로젝트는 발전 가능성을 생각하고 만들었습니다. 모범답안이 아니니 fork마구 고치세요.  

# Start from this commit

문서의 첫 부분에 'Start from this commit'이 있는 경우에는 해당 커밋으로 이동하고 브랜치를 따서 직접 해보세요.

1. **git checkout <커밋 해쉬값>**:특정 커밋으로 이동한다.

2. **git checkout -b <브랜치 이름>**:지금 위치하고 있는 커밋에서 새로운 브랜치를 만든다. 
