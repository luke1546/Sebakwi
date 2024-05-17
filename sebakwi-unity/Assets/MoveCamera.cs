using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveCamera : MonoBehaviour
{
    public float moveSpeed = 10f;
    public float lookSpeed = 2f;

    private float yaw = 209f;
    private float pitch = 0f;
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        // Movement
        float horizontal = Input.GetAxis("Horizontal");
        float vertical = Input.GetAxis("Vertical");
        float upDown = 0f;

        if (Input.GetKey(KeyCode.Q))
        {
            upDown = -1f;
        }
        if (Input.GetKey(KeyCode.E))
        {
            upDown = 1f;
        }

        Vector3 direction = new Vector3(horizontal, upDown, vertical);
        transform.Translate(direction * moveSpeed * Time.deltaTime);

        // Mouse look
        if (Input.GetMouseButton(1))
        {
            yaw += lookSpeed * Input.GetAxis("Mouse X");
            pitch -= lookSpeed * Input.GetAxis("Mouse Y");

            transform.eulerAngles = new Vector3(pitch, yaw, 0.0f);
        }
    }
}
