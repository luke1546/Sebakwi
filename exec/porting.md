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

## Back-End

1. Srping
    
    ### 환경 변수 설정
    ```yml
    # application.yml

    spring:
        jackson:
            sort-properties-alphabetically: false
        jpa:
            hibernate:
            ddl-auto: create
            show-sql: true
            generate-ddl: true
            properties:
            hibernate:
                format_sql: true
            defer-datasource-initialization: true
        servlet:
            multipart:
            max-file-size: 100MB
            max-request-size: 100MB

        profiles:
            active: secret

    # application-secret.yml

    spring:
        datasource:
            url: jdbc:postgresql://localhost:5432/sebakwi
            username: postgres
            password: root
            driver-class-name: org.postgresql.Driver

        mqtt:
            url: tcp://localhost:1883
            topic: checkup/+
            foupstocker-switch-topic: foupstocker/switch
            etching-switch-topic: etching/switch
            cleaning-switch-topic: cleaning/switch
            photo-switch-topic: photo/switch
            username: yourUsername
            password: yourPassword
            client-id: springClient
    ```


    ### 빌드 & 실행
    ```
    # Gradle 프로젝트 루트 디렉토리에서 실행

    # Gradle 빌드
    ./gradlew build

    # build/libs 디렉토리 확인
    ls build/libs

    # 애플리케이션 실행 (생성된 JAR 파일 이름 사용)
    java -jar build/libs/[생성된 JAR 파일 이름].jar
    ```

2. PostgreSQL
    ```
    # PostgreSQL 설치후 
    # 데이터베이스 생성
    CREATE DATABASE sebakwi;

## Jetson Nano

    ```
    # 환경설정
    sudo apt-get update
    sudo apt upgrade -y
    sudo apt-get autoremove
    # *** nvidia-tegra.conf (Y/I/N/O/D/Z) [default=N] ? -> 모두 n
    # Automatically restart Docker daemon? -> Yes


    # Create SwapFile:
    sudo fallocate -l 4G /var/swapfile 
    sudo chmod 600 /var/swapfile
    sudo mkswap /var/swapfile
    sudo swapon /var/swapfile
    sudo bash -c 'echo "/var/swapfile swap swap defaults 0 0"  >> /etc/fstab'

    # Reboot your PC
    sudo reboot

    # After rebooting check swap space  by using this command:
    free -h


    # Install these Dependencies before installing OpenCV:
    sudo sh -c "echo '/usr/local/cuda/lib64' >> /etc/ld.so.conf.d/nvidia-tegra.conf"
    sudo ldconfig
    sudo apt-get install build-essential cmake git unzip pkg-config -y
    sudo apt-get install libjpeg-dev libpng-dev libtiff-dev -y
    sudo apt-get install libavcodec-dev libavformat-dev libswscale-dev -y
    sudo apt-get install libgtk2.0-dev libcanberra-gtk* -y
    sudo apt-get install python3-dev python3-numpy python3-pip -y
    sudo apt-get install libxvidcore-dev libx264-dev libgtk-3-dev -y
    sudo apt-get install libtbb2 libtbb-dev libdc1394-22-dev -y
    sudo apt-get install libv4l-dev v4l-utils -y
    sudo apt-get install libgstreamer1.0-dev libgstreamer-plugins-base1.0-dev -y
    sudo apt-get install libavresample-dev libvorbis-dev libxine2-dev -y
    sudo apt-get install libfaac-dev libmp3lame-dev libtheora-dev -y
    sudo apt-get install libopencore-amrnb-dev libopencore-amrwb-dev -y
    sudo apt-get install libopenblas-dev libatlas-base-dev libblas-dev -y
    sudo apt-get install liblapack-dev libeigen3-dev gfortran -y
    sudo apt-get install libhdf5-dev protobuf-compiler -y
    sudo apt-get install libprotobuf-dev libgoogle-glog-dev libgflags-dev -y

    # Download OpenCV:
    cd ~/
    wget -O opencv.zip https://github.com/opencv/opencv/archive/4.5.1.zip 
    wget -O opencv_contrib.zip https://github.com/opencv/opencv_contrib/archive/4.5.1.zip 
    unzip opencv.zip 
    unzip opencv_contrib.zip

    # Now rename the directories. Type each command below, one after the other.
    mv opencv-4.5.1 opencv
    mv opencv_contrib-4.5.1 opencv_contrib
    rm opencv.zip
    rm opencv_contrib.zip

    # Lets build OpenCV now:
    cd ~/opencv
    mkdir build
    cd build 


    # copy and paste this entire block of commands below into your terminal.

    cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr -D OPENCV_EXTRA_MODULES_PATH=~/opencv_contrib/modules -D EIGEN_INCLUDE_PATH=/usr/include/eigen3 -D WITH_OPENCL=OFF -D WITH_CUDA=ON -D CUDA_ARCH_BIN=5.3 -D CUDA_ARCH_PTX="" -D WITH_CUDNN=ON -D WITH_CUBLAS=ON -D ENABLE_FAST_MATH=ON -D CUDA_FAST_MATH=ON -D OPENCV_DNN_CUDA=ON -D ENABLE_NEON=ON -D WITH_QT=OFF -D WITH_OPENMP=ON -D WITH_OPENGL=ON -D BUILD_TIFF=ON -D WITH_FFMPEG=ON -D WITH_GSTREAMER=ON -D WITH_TBB=ON -D BUILD_TBB=ON -D BUILD_TESTS=OFF -D WITH_EIGEN=ON -D WITH_V4L=ON -D WITH_LIBV4L=ON -D OPENCV_ENABLE_NONFREE=ON -D INSTALL_C_EXAMPLES=OFF -D INSTALL_PYTHON_EXAMPLES=OFF -D BUILD_NEW_PYTHON_SUPPORT=ON -D BUILD_opencv_python3=TRUE -D OPENCV_GENERATE_PKGCONFIG=ON -D BUILD_EXAMPLES=OFF ..

    # Build OpenCV. This command below will take a long time (around 2 hours), 
    # (make then space single dash and then j4)
    make -j4     

    # Finish the install:
    cd ~
    sudo rm -r /usr/include/opencv4/opencv2
    cd ~/opencv/build
    sudo make install
    sudo ldconfig
    make clean
    sudo apt-get update 

    # Verify OpenCV Installation
    #open python3 shell
    python3
    import cv2
    cv2._version_


    # set venv
    python3 -m pip install virtualenv
    python3 -m virtualenv -p python3 env --system-site-packages
    source env/bin/activate

    # pytorch v1.8.0
    wget https://nvidia.box.com/shared/static/p57jwntv436lfrd78inwl7iml6p13fzh.whl -O torch-1.8.0-cp36-cp36m-linux_aarch64.whl
    sudo apt-get install python3-pip libopenblas-base libopenmpi-dev libomp-dev
    pip3 install 'Cython<3'
    pip3 install numpy torch-1.8.0-cp36-cp36m-linux_aarch64.whl


    # torchvision v.0.9.0
    sudo apt-get install libjpeg-dev zlib1g-dev libpython3-dev libopenblas-dev libavcodec-dev libavformat-dev libswscale-dev -y
    git clone --branch v0.9.0 https://github.com/pytorch/vision torchvision
    cd torchvision
    export BUILD_VERSION=0.9.0
    python3 setup.py install --user
    cd ../
    # pip install 'pillow<9'  future feature annotations is not defined 오류가난다면 설치


    # git clone
    cd  /home/ssafy
    git clone https://lab.ssafy.com/s10-final/S10P31S108

    ## Setting PYTHONPATH 
    export PYTHONPATH=$PYTHONPATH:/home/ssafy/S10P31S108/Sebakwi-emb
    source ~/.bashrc
    

    vim /home/ssafy/myenv/bin/activate
    export PYTHONPATH=$PYTHONPATH:/home/ssafy/S10P31S108/Sebakwi-emb
    export PYTHONPATH=$PYTHONPATH:/home/ssafy/S10P31S108/Sebakwi-emb/yolov5
    export PYTHONPATH=$PYTHONPATH:/home/ssafy/S10P31S108/Sebakwi-emb/yolov5/models
    export PYTHONPATH=$PYTHONPATH:/home/ssafy/S10P31S108/Sebakwi-emb/yolov5/utils
    source ~/.bashrc

    ## 서비스 실행
    pip install -r /home/ssafy/S10P31S108/Sebakwi-emb/embedded_system/requirements.txt
    python /home/ssafy/S10P31S108/Sebakwi-emb/embedded_system/main.py

    ```