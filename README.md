# 세바퀴 : OHT 휠 이상 감지 진단 AI

## 세바퀴 서비스 개요

- 배경 정리해서 쓰기 

### 프로젝트 기간
- 2024.04.08 - 2023.05.20

### 팀원 소개
|이름|역할|담당업무|
|:---:|:---:|:---|
|오지훈|팀장, Front-End| UI/UX 디자인/ front 기초 세팅 및 폴더 구조 생성, 공용 컴포넌트 제작, MainHeader, 검진 목록 페이지 - PageNation, filter 검색, AI 데이터 라벨링 |
|전재우|AI| 굳굳|
|신성현|Infra, Jetson Nano| 굳굳|
|유지원|Front-End| 굳굳|
|이진성|Back-End| 굳굳|
|이호성|Front-End, Unity| unity 시뮬레이터 제작, front 바퀴 상세페이지, three js, 데이터 라벨링, 모델학습|

<hr>

## 주요 기능 소개

1. 메인 화면
<table width="100%" border-style="non" cellspacing="0" cellpadding="100">
  <tr>
    <td align="center"><img src="readme_image/main_page_1.gif" alt="main_page" width="200"></td>
    <td align="center"><img src="readme_image/main_page_2.gif" alt="video_page" width="200"></td>
  </tr>

</table>

2. Unity 시뮬레이터
<table width="100%" border-style="non" cellspacing="0" cellpadding="100">
  <tr>
    <td align="center"><img src="readme_image/unity.gif" alt="result_page" width="200"></td>
    <td align="center"><img src="readme_image/unity.gif" alt="galery_page" width="200"></td>
  </tr>
</table>

<br/><br/>
- 세바퀴 페이지 보여주기

<hr>

## 시스템 아키텍처


<hr>

## ERD

<hr>

# 🛠 주요 기술


**Backend**
<br>

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/fastapi-009688?style=for-the-badge&logo=fastapi&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
- Java : Oracle OpenJDK 11.0.17
- SpringBoot 2.7.9
- Spring Security 5.7.7
- Spring Data Jpa 2.7.9
- Spring Boot Actuator
- Junit 5.8.2
- Gradle 7.6.1
- FastAPI
- MySQL 운영서버 : 8.0.28, 개발서버 : 8.0.32

<br>

**FrontEnd**
<br>

<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black">&nbsp;<img src="https://img.shields.io/badge/styled components-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Three.js-1A1A1A?style=for-the-badge&logo=three.js&logoColor=white">

- React 18.2.0
- Node.js 20.11.0
- TypeScript 4.9.5
- Styled-component 6.1.8
- Axios 1.3.5
- three 0.164.1

<br>

**CI/CD**
<br>

<img src="https://img.shields.io/badge/aws ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/openssl-721412?style=for-the-badge&logo=openssl&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/sonarqube-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white">&nbsp;


- AWS EC2
- Ubuntu 20.04 LTS
- Jenkins 2.387.1
- Docker Engine 23.0.1
- Nginx 1.23.4
- SSL
- SonarQube 
- Grafana latest
- Prometheus 2.44.0
- Ngrinder-controller 3.5.8
- Ngrinder-agent 3.5.8

<br>

**협업 툴**
<br>

<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jirasoftware&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/figma-EA4335?style=for-the-badge&logo=figma&logoColor=white">&nbsp;
- 형상 관리 : Git
- 이슈 관리 : Jira
- 커뮤니케이션 : Mattermost, Webex, Notion
- 디자인 : Figma


<br>
