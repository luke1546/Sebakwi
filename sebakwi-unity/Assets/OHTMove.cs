using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class OHTMove : MonoBehaviour
{
    public GameObject DropOff;
    public GameObject StretchWire;
    Vector3[] destinations = new Vector3[5];
    private bool moveFlag = true;
    private bool dropFlag = false;
    private int index = 0;
    private bool isRotating = false; // 회전 상태를 관리할 플래그
    private Quaternion targetRotation; // 목표 회전 값
    private float speed = 2;
    private float acc = 0.015f;

    void Start()
    {
        destinations[0] = new Vector3(-6.36f, 5.4f, -11.63f);
        destinations[1] = new Vector3(4.5f, 5.4f, -11.63f);
        destinations[2] = new Vector3(15.35f, 5.4f, -11.63f);
        destinations[3] = new Vector3(15.35f, 5.4f, -19f);
    }

    void Update()
    {
        if (moveFlag && !isRotating)
        {
            transform.position = Vector3.MoveTowards(transform.position, destinations[index], speed * Time.deltaTime);
            speed += acc;
        }

        // 목적지에 도착하면 회전을 시작
        if (transform.position == destinations[index] && !isRotating)
        {
            if (index == 0)
            {
                targetRotation = Quaternion.Euler(transform.eulerAngles + new Vector3(0, 90, 0));
                isRotating = true;
            }
            if (index == 2)
            {
                targetRotation = Quaternion.Euler(transform.eulerAngles + new Vector3(0, 90, 0));
                isRotating = true;
            }
            if (index == 3) Destroy(gameObject); // 마지막 목적지 도달 시 객체 파괴
            index++;
        }

        if (isRotating)
        {
            // Quaternion.Lerp를 사용하여 부드럽게 회전
            transform.rotation = Quaternion.Lerp(transform.rotation, targetRotation, Time.deltaTime * 5);
            // 회전이 거의 완료되었는지 확인
            if (Quaternion.Angle(transform.rotation, targetRotation) < 0.01f)
            {
                transform.rotation = targetRotation; // 최종 회전값을 정확하게 맞춤
                isRotating = false; // 회전 완료
                speed = 2;
            }
        }

        if (index == 2 && moveFlag && !dropFlag)
        {
            moveFlag = false;
            DropOff.GetComponent<DropOff>().drop();
            StretchWire.GetComponent<StretchWire>().stretch();
            dropFlag = true;
        }
    }

    public void move()
    {
        speed = 2;
        dropFlag = true;
        moveFlag = true;
    }
}
