from scapy.all import *
from os import remove
import time

class Send():
    def __init__(self,interface=None,path=None,proto=0x0800):
        '''
        :param interface: the port you send packets
        :param path: where is the pcap packets
        :param proto: packets' proto
        '''
        self.int = interface
        self.path = path
        self.proto = proto
        self.flag = 0
        if self.int !=None:
            self.send()

    def send(self):
        sockets = socket.socket(family=socket.PF_PACKET, type=socket.SOCK_RAW, proto=socket.htons(self.proto))
        sockets.bind((self.int, socket.htons(self.proto)))
        print("loading packets..")
        packets = rdpcap(self.path)

        for i in range(len(packets)):
            print(repr(packets[i]))
            sockets.sendall(bytes(packets[i]))
	        # sockets.sendall(packets[i])


if __name__ == '__main__':
    Send(interface='ens33',path='/home/mao/Mao/Documents/tcp_udp_test.pcap')
