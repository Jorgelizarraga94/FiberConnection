<img width="1442" height="508" alt="PrtScr capture" src="https://github.com/user-attachments/assets/de51bb06-e1dc-4cf3-bfb7-4c2bcb286ecf" />
<img width="553" height="345" alt="image" src="https://github.com/user-attachments/assets/d32619aa-375c-4e33-9f53-66eeae4b04bb" />
En este caso, se termina decidiendo que FiberConnection actuará como la clase Principal que se encarga de toda las llamadas y lógica de control para el resto de clases.
Grafo se encargará de recibir la información por usuario y comunicarse con Localidad (Nodo) y Conexión (Arista) para construir finalmente un grafo. 
AGM recibirá la información del grafo (y la del planificador de ser necesario por cuentas de peso), para finalmente contruir el camino mínimo y devolverlo al planificador para así calcular los costos. 
