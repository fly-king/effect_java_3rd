import matplotlib.pyplot as plt
import numpy as np

n = 12
X =np.array([ 2,
              3,
              16,
              17,
              31,
              32,
              43,
              101])
Y1 = np.array([309871,
               136410,
               149633,
               3169,
               624,
               241206,
               223,
               29])

Y2 = np.array([64.5793,
               28.4288,
               31.1846,
               0.6604,
               0.1300,
               50.2691,
               0.0465,
               0.0060])

X2 =np.array([
              3,
              17,
              31,
              43,
              101])

Y3 = np.array([
               28.4288,
               0.6604,
               0.1300,
               0.0465,
               0.0060])


plt.subplot(2, 1, 1)
plt.plot(X, Y1, 'r')
plt.subplot(2, 1, 2)
plt.plot(X2, Y3, 'g')

plt.show()