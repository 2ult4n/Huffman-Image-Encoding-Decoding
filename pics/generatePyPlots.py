import matplotlib.pyplot as plt
import numpy as np 
import csv
import os
import itertools

def create_plot(ptype): 
    # setting the x-axis vaues 
    x = np.arange(0, 1000,0.1) 
      
    # setting the y-axis values 
    if ptype == 'linear': 
        y = x
    elif ptype == 'quadratic': 
        y = (x**2)

              
    return(x, y) 


x = []
y = []

with open('C:/Users/isult/Desktop/plotStat.csv','r') as csvfile:
    plots = csv.reader(csvfile, delimiter=',')
    for row in plots:
        x.append((int)(row[0]))
        y.append((int)(row[1]))

x1, y1 = create_plot('linear') 

plt.plot(y,x, label='performance over time')

plt.xlabel('Turnaround Time')
plt.ylabel('Waiting Time')
plt.title('Second Sample')
plt.legend()
plt.show()