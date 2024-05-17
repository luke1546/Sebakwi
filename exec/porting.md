# 포팅 가이드(Porting Guide)

## Clone to Run

```bash
git clone https://lab.ssafy.com/s08-final/S08P31A301.git
```

    

## Front-End 

1. Node.js (20.11.1) 다운로드
    
    [https://nodejs.org/en/blog/release/v20.11.1](https://nodejs.org/en/blog/release/v20.11.1)
    
2. 패키지 다운로드
    
    ```bash
    # /Sebakwi-fe
    npm install
    ```
    
3. create .env file
    
    ```bash
    # /Sebakwi-fe/.env
    
    GENERATE_SOURCEMAP=false
    REACT_APP_BASE_URL=http://localhost:8080/api
    ```
    
4. run react app
    
    ```bash
    npm start
    ```
