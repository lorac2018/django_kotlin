from rest_framework import serializers
from . import models
from .models import UserProfile, Garrafa, Oleao
from django.contrib.auth.models import User
from rest_framework import serializers
from rest_framework.validators import UniqueValidator



class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username','first_name', 'email', 'last_name')


class UserProfileSerializer (serializers.ModelSerializer):
    class Meta:
        model = UserProfile
        fields = ('id', 'address')

class PointsSerializer (serializers.ModelSerializer):
    class Meta:
        model = UserProfile
        fields = ('id','points')

class OleaoSerializer (serializers.ModelSerializer):
    class Meta:
        model = Oleao
        fields = ('id','rfid','location')

class GarrafaSerializer (serializers.ModelSerializer):
    class Meta:
        model = Garrafa
        fields = ('id',)
