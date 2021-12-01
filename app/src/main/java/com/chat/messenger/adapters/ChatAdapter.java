package com.chat.messenger.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chat.messenger.databinding.ItemContainerReceiveMessageBinding;
import com.chat.messenger.databinding.ItemContainerSentMessageBinding;
import com.chat.messenger.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;
    private final List<ChatMessage> chatMessages;
    private final String senderId;
    private Bitmap receiverProfileImage;

    public void setReceiverProfileImage(Bitmap bitmap) {
        receiverProfileImage = bitmap;
    }

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.receiverProfileImage = receiverProfileImage;
        this.chatMessages = chatMessages;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            return new SentMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        } else {
            return new ReceiveMessageViewHolder(
                    ItemContainerReceiveMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
        } else {
            ((ReceiveMessageViewHolder) holder).setData(chatMessages.get(position), receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage chatMessage) {

            if (chatMessage.message.length() > 1) {
                binding.textMessage.setText(chatMessage.message);
                binding.chatImage.setVisibility(View.GONE);
                // binding.textDateTime1.setVisibility(View.INVISIBLE);
                binding.textDateTime.setText(chatMessage.dateTime);
            } else {
                binding.chatImage.setImageBitmap(getChatImage(chatMessage.chatImage));
                binding.textMessage.setVisibility(View.GONE);
                //binding.textDateTime.setVisibility(View.INVISIBLE);
                binding.textDateTime1.setText(chatMessage.dateTime);
            }

        }
    }

    private static Bitmap getChatImage(String encodedChatImage) {
        byte[] bytes = Base64.decode(encodedChatImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    }

    static class ReceiveMessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerReceiveMessageBinding binding;

        ReceiveMessageViewHolder(ItemContainerReceiveMessageBinding itemContainerReceiveMessageBinding) {
            super(itemContainerReceiveMessageBinding.getRoot());
            binding = itemContainerReceiveMessageBinding;
        }

        void setData(ChatMessage chatMessage, Bitmap receiverProfileImage) {

            {
                if (chatMessage.message.length() > 1) {
                    binding.textMessage.setText(chatMessage.message);
                    binding.chatImage.setVisibility(View.GONE);
                    // binding.textDateTime1.setVisibility(View.INVISIBLE);
                    binding.textDateTime.setText(chatMessage.dateTime);
                } else {
                    binding.chatImage.setImageBitmap(getChatImage(chatMessage.chatImage));
                    binding.textMessage.setVisibility(View.GONE);
                    //binding.textDateTime.setVisibility(View.INVISIBLE);
                    binding.textDateTime1.setText(chatMessage.dateTime);
                }

            }
            if (receiverProfileImage != null) {
                binding.imageProfile.setImageBitmap(receiverProfileImage);
            }
        }

    }
}
