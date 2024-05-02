using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DropOff : MonoBehaviour
{
    // Start is called before the first frame update
    Vector3 destination;
    private bool dropFlag = false;



    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        if (dropFlag)
        {
            transform.position = Vector3.MoveTowards(transform.position, destination, 3 * Time.deltaTime);
        }

        if (transform.position == destination && dropFlag)
        {
            dropFlag = false;
        }
    }

    public void drop()
    {
        destination = new Vector3(transform.position[0], transform.position[1] - 4, transform.position[2]);
        transform.parent = null;
        dropFlag = true;
    }
}
