using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveCamera : MonoBehaviour
{
    private Vector3 destination;
    private float time = 0;
    // Start is called before the first frame update
    void Start()
    {
        destination = new Vector3(transform.position[0], transform.position[1], transform.position[2] - 13f);
    }

    // Update is called once per frame
    void Update()
    {
        if (time < 6) time += Time.deltaTime;
        else transform.position = Vector3.MoveTowards(transform.position, destination, 4 * Time.deltaTime);
    }
}
